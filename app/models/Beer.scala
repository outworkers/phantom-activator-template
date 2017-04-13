package models

import com.outworkers.phantom.dsl._

import scala.concurrent.Future

case class Beer(
  company: String,
  name: String,
  style: String
)

abstract class Beers extends CassandraTable[Beers, Beer] with RootConnector {
  object company extends StringColumn(this) with PartitionKey
  object name extends StringColumn(this) with PartitionKey
  object style extends StringColumn(this) with Index

  def getByStyle(style: String): Future[List[Beer]] = {
    select.where(_.style eqs style).fetch()
  }

}
