package models

import com.websudos.phantom.connectors.KeySpaceDef
import com.websudos.phantom.dsl.Database

class AppDatabase(keyspace: KeySpaceDef) extends Database(keyspace) {

  object users extends ConcreteUsers with keyspace.Connector
  object beers extends ConcreteBeers with keyspace.Connector
}
