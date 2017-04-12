package models

import com.outworkers.phantom.dsl._
import scala.concurrent.Future

case class User(
  id: UUID,
  email: String,
  name: String,
  passwordHash: String,
  salt: String,
  registration: DateTime
)

abstract class Users extends CassandraTable[Users, User] with RootConnector {
  object id extends UUIDColumn(this) with PartitionKey
  object email extends StringColumn(this)
  object name extends StringColumn(this)
  object passwordHash extends StringColumn(this)
  object salt extends StringColumn(this)
  object registration extends DateTimeColumn(this)


  def getById(id: UUID): Future[Option[User]] = {
    select.where(_.id eqs id).one()
  }

  def deleteById(id: UUID): Future[ResultSet] = {
    delete.where(_.id eqs id).future()
  }
}
