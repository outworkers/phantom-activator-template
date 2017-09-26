package controllers

import java.io.File
import java.util.concurrent.atomic.AtomicBoolean

import org.cassandraunit.utils.EmbeddedCassandraServerHelper
import org.slf4j.Logger

import scala.concurrent.blocking
import scala.util.control.NonFatal
import scala.util.{Failure, Success, Try}

/**
 * Taken from
 *
 * https://raw.githubusercontent.com/outworkers/phantom/develop/phantom-sbt/src/main/scala-2.10/com/outworkers/phantom/sbt/SbtPlugin.scala
 *
 * This solves for https://github.com/outworkers/phantom/issues/649
 */
object EmbeddedCassandra {

  private[this] val started: AtomicBoolean = new AtomicBoolean(false)

  /**
   * Starts Cassandra in embedded mode if it has not been
   * started yet.
   */
  def start(logger: Logger, config: Option[File] = None, timeout: Option[Int] = None): Unit = {
    this.synchronized {
      if (started.compareAndSet(false, true)) {
        blocking {
          val configFile = config.map(_.toURI.toString) getOrElse EmbeddedCassandraServerHelper.DEFAULT_CASSANDRA_YML_FILE
          System.setProperty("cassandra.config", configFile)
          Try {
            EmbeddedCassandraServerHelper.mkdirs()
          } match {
            case Success(value) => logger.info("Successfully created directories for embedded Cassandra.")
            case Failure(NonFatal(e)) =>
              logger.error(s"Error creating Embedded cassandra directories: ${e.getMessage}")
          }

          (config, timeout) match {
            case (Some(file), None) =>
              logger.info(s"Starting Cassandra in embedded mode with configuration from $file.")
              EmbeddedCassandraServerHelper.startEmbeddedCassandra(
                file,
                EmbeddedCassandraServerHelper.DEFAULT_TMP_DIR,
                EmbeddedCassandraServerHelper.DEFAULT_STARTUP_TIMEOUT
              )
            case (Some(file), Some(time)) =>
              logger.info(s"Starting Cassandra in embedded mode with configuration from $file and timeout set to $timeout ms.")
              EmbeddedCassandraServerHelper.startEmbeddedCassandra(
                file,
                EmbeddedCassandraServerHelper.DEFAULT_TMP_DIR,
                time
              )

            case (None, Some(time)) =>
              logger.info(s"Starting Cassandra in embedded mode with default configuration and timeout set to $timeout ms.")
              EmbeddedCassandraServerHelper.startEmbeddedCassandra(time)
            case (None, None) =>
              logger.info("Starting Cassandra in embedded mode with default configuration.")
              EmbeddedCassandraServerHelper.startEmbeddedCassandra()
              logger.info("Successfully started embedded Cassandra")
          }
        }
      }
      else {
        logger.info("Embedded Cassandra has already been started")
      }
    }
  }


  def cleanup(logger: Logger): Unit = {
    this.synchronized {
      if (started.compareAndSet(true, false)) {
        logger.info("Cleaning up embedded Cassandra")
        EmbeddedCassandraServerHelper.cleanEmbeddedCassandra()
      } else {
        logger.info("Cassandra is not running, not cleaning up")
      }
    }
  }
}