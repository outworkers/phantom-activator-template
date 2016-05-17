package models

import com.websudos.phantom.dsl._

trait Connector {
  implicit def space: KeySpace

  implicit def session: Session
}

object Defaults {
  val Connector = ContactPoint.local.keySpace("websudos")
}


class AppDatabase(val keyspace: KeySpaceDef) extends Database(keyspace) {

  object users extends ConcreteUsers with keyspace.Connector
  object beers extends ConcreteBeers with keyspace.Connector
}

object AppDatabase extends AppDatabase(Defaults.Connector)

trait DatabaseProvider {
  def database: Database
}

trait AppDatabaseProvider extends DatabaseProvider {
  override val database: AppDatabase = AppDatabase
}