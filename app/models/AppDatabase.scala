package models

import com.websudos.phantom.connectors.{ContactPoint, KeySpaceDef}
import com.websudos.phantom.dsl._

object Defaults {
  val Connector = ContactPoint.local.keySpace("websudos")
}

class AppDatabase(val keyspace: KeySpaceDef) extends Database(keyspace) {

  object users extends ConcreteUsers with keyspace.Connector
  object beers extends ConcreteBeers with keyspace.Connector
}


object AppDatabase extends AppDatabase(Defaults.Connector)