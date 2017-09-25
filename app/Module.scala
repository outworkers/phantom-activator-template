import javax.inject.{Provider, Singleton}

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
class ConnectionProvider extends Provider[CassandraConnection] {
  lazy val get: CassandraConnection = ContactPoint.local.keySpace("outworkers")
}
