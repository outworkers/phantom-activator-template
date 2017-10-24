package models

import com.outworkers.phantom.dsl._

import scala.concurrent.Future

import com.outworkers.phantom.macros.debug.Options.ShowLog
import com.outworkers.phantom.macros.debug.Options.ShowTrees
import com.outworkers.phantom.macros.debug.Options.ShowAborts
import com.outworkers.phantom.macros.debug.Options.ShowCache

case class Beer(
  company: String,
  name: String,
  style: String
)

abstract class Beers extends Table[Beers, Beer] with RootConnector {
  object company extends StringColumn with PartitionKey
  object name extends StringColumn with PartitionKey
  object style extends StringColumn with Index

  def getByStyle(style: String): Future[Seq[Beer]] = {
    select.where(_.style eqs style).fetch()
  }

  def all(): Future[Seq[Beer]] = {
    select.all.fetch()
  }
}
