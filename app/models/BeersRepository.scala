package models

import com.google.inject.Inject

import scala.concurrent.Future

class BeersRepository @Inject()(db: AppDatabase) {

  def getByStyle(style: String): Future[Seq[Beer]] = {
    db.beers.getByStyle(style)
  }

  def all: Future[Seq[Beer]] = db.beers.all()
}
