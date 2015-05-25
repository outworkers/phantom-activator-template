package models

import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._

import scala.concurrent.Future

case class Beer(company: String, name: String, style: String)

class Beers extends CassandraTable[Beers, Beer] {
  object company extends StringColumn(this) with PartitionKey[String]
  object name extends StringColumn(this) with PartitionKey[String]
  object style extends StringColumn(this) with Index[String]

  def fromRow(row: Row): Beer = {
    Beer(company(row), name(row), style(row))
  }

}

object Beers extends Beers with SimpleCassandraConnector {

  override implicit def keySpace: KeySpace = KeySpace("beers")

  def getByStyle(style: String): Future[List[Beer]] = {
    Beers.select.where(_.style eqs style).fetch()
  }
  
}