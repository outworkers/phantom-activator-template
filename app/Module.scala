import javax.inject.{Inject, Provider, Singleton}

import com.google.inject.AbstractModule
import com.outworkers.phantom.connectors.{CassandraConnection, ContactPoint}
import models.AppDatabase
import net.codingwell.scalaguice.ScalaModule

class Module extends AbstractModule  with ScalaModule {
  override def configure(): Unit = {
    bind[CassandraConnection].toProvider[ConnectionProvider]
    bind[AppDatabase].asEagerSingleton()
  }
}

@Singleton
class ConnectionProvider @Inject()(env: play.api.Environment) extends Provider[CassandraConnection] {
  lazy val get: CassandraConnection = {
    val builder = env.mode match {
      case play.api.Mode.Test =>
        ContactPoint.embedded
      case other@_ =>
        ContactPoint.local
    }
    builder.keySpace("outworkers")
  }
}
