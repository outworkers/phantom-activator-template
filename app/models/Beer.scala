package models

import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._

import scala.concurrent.Future

case class Beer(company: String, name: String, style: String)

class Beers extends CassandraTable[ConcreteBeers, Beer] {
  object company extends StringColumn(this) with PartitionKey[String]
  object name extends StringColumn(this) with PartitionKey[String]
  object style extends StringColumn(this) with Index[String]

  def fromRow(row: Row): Beer = {
    Beer(
      company = company(row),
      name = name(row),
      style = style(row)
    )
  }

}

abstract class ConcreteBeers extends Beers with RootConnector {

  def getByStyle(style: String): Future[List[Beer]] = {
    select.where(_.style eqs style).fetch()
  }
  
}