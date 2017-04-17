package models

import com.outworkers.phantom.builder.query.CreateQuery
import com.outworkers.phantom.connectors.{ ContactPoint, CassandraConnection }
import com.outworkers.phantom.dsl._
import scala.concurrent.duration._

object Defaults {
  val Connector: CassandraConnection = ContactPoint.local.keySpace("websudos")
}

class AppDatabase(override val connector: CassandraConnection) extends Database[AppDatabase](connector) {

  object users extends Users with Connector {
    override def autocreate(space: KeySpace): CreateQuery.Default[Users, User] = {
      create.ifNotExists()(space)
        .`with`(default_time_to_live eqs 10)
        .and(gc_grace_seconds eqs 10.seconds)
        .and(read_repair_chance eqs 0.2)
    }
  }
  object beers extends Beers with Connector
}


object AppDatabase extends AppDatabase(Defaults.Connector)
