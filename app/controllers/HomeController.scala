package controllers

import javax.inject.Inject

import models._
import play.api.mvc._

import scala.concurrent.ExecutionContext

class HomeController @Inject()(repo: BeersRepository, cc: ControllerComponents)(implicit ec: ExecutionContext) extends AbstractController(cc) {

  def index(style: Option[String]): Action[AnyContent] = Action.async {
    style match {
      case Some(s) =>
        repo.getByStyle(s).map { beers =>
          Ok(views.html.index(beers))
        }

      case None =>
        repo.all.map { beers =>
          Ok(views.html.index(beers))
        }
    }
  }
}
