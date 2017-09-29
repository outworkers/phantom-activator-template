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

    def initialize(): Future[List[ResultSet]] = {
      val beerList = List(
        Beer(company = "budhizer", style = "white", name = "Summer Bud"),
        Beer(company = "budhizer", style = "dark", name = "Winter Bud"),
        Beer(company = "budhizer", style = "wheat", name = "Spring Bud"),
        Beer(company = "budhizer", style = "pumpkin", name = "Fall Bud")
      )

      for {
        init <- beers.autocreate(space).future()

        // https://github.com/outworkers/phantom/blob/develop/docs/basics/tables.md#automatically-derived-store-method
        records <- beers.storeRecords(beerList)
      } yield records
    }
  }

  // Runs in constructor when the application is started.  Because this is @Singleton,
  // it runs only once, only one instance of this class is created.
  env.mode match {
    case play.api.Mode.Test =>
      beers.initialize()
    case other@_ =>
      Seq.empty
  }
}
