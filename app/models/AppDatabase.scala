package models

import javax.inject.{Inject, Singleton}

import com.outworkers.phantom.builder.query.CreateQuery
import com.outworkers.phantom.dsl._

import scala.concurrent.{Await, Future}
import scala.concurrent.duration._

@Singleton
class AppDatabase @Inject()(env: play.api.Environment, override val connector: CassandraConnection) extends Database[AppDatabase](connector) {

  object beers extends Beers with Connector {
    override def autocreate(space: KeySpace): CreateQuery.Default[Beers, Beer] = {
      create.ifNotExists()(space)
        .option(default_time_to_live eqs 10)
        .and(gc_grace_seconds eqs 10.seconds)
        .and(read_repair_chance eqs 0.2)
    }

    def save(beer: Beer): Future[ResultSet] = {
      beers.insert
        .value(_.company, beer.company)
        .value(_.style, beer.style)
        .value(_.name, beer.name)
        .future()
    }

    def initialize(): Unit = {
      Await.result(beers.autocreate(space).future(), 5000 millis)
      beers.save(Beer(company = "budhizer", style = "white", name = "Summer Bud"))
      beers.save(Beer(company = "budhizer", style = "dark", name = "Winter Bud"))
      beers.save(Beer(company = "budhizer", style = "wheat", name = "Spring Bud"))
      beers.save(Beer(company = "budhizer", style = "pumpkin", name = "Fall Bud"))
    }
  }

  // Create cassandra database schema and populate database in test mode only.
  env.mode match {
    case play.api.Mode.Test =>
      beers.initialize()
    case other@_ =>
      // do nothing
  }
}
