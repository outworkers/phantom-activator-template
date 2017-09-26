package controllers

import org.scalatest.{BeforeAndAfterAll, MustMatchers, WordSpec}
import org.scalatestplus.play.guice.GuiceOneAppPerTest
import play.api.test.Helpers._
import play.api.test._

/**
 * Run functional tests using WithApplication
 */
class HomeControllerSpec extends WordSpec with GuiceOneAppPerTest with MustMatchers with BeforeAndAfterAll {

  private val logger = org.slf4j.LoggerFactory.getLogger("embedded-cassandra")

  // https://github.com/jsevellec/cassandra-unit/blob/master/cassandra-unit/src/main/java/org/cassandraunit/utils/EmbeddedCassandraServerHelper.java#L48
  "Application" should {

    "render the index page" in {
      val result = route(app, FakeRequest(GET, "/")).get
      status(result) must equal(OK)
      contentAsString(result) must include("Spring Bud")
    }
  }

  override protected def beforeAll(): Unit = {
    //System.setProperty("log4j.configuration", "conf/log4j.properties")
    EmbeddedCassandra.start(logger)
  }

  override protected def afterAll(): Unit = {
    EmbeddedCassandra.cleanup(logger)
  }
}
