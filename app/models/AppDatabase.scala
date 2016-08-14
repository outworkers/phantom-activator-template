package models

import com.websudos.phantom.builder.query.CreateQuery
import com.websudos.phantom.connectors.{ContactPoint, KeySpaceDef}
import com.websudos.phantom.dsl._
import scala.concurrent.duration._

object Defaults {
  val Connector = ContactPoint.local.keySpace("websudos")
}

class AppDatabase(val keyspace: KeySpaceDef) extends Database(keyspace) {

  object users extends ConcreteUsers with keyspace.Connector {
    override def autocreate(space: KeySpace): CreateQuery.Default[ConcreteUsers, User] = {
      create.ifNotExists().`with`(default_time_to_live eqs 10)
        .and(gc_grace_seconds eqs 10.seconds)
        .and(read_repair_chance eqs 0.2)
    }
  }
  object beers extends ConcreteBeers with keyspace.Connector
}


object AppDatabase extends AppDatabase(Defaults.Connector)