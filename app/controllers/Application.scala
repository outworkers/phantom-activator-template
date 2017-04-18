package controllers

import models.AppDatabase
import play.api.mvc._

import scala.concurrent.ExecutionContext.Implicits.global

class Application extends Controller {

  def index: Action[AnyContent] = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def beers(style: String): Action[AnyContent] = Action.async {
    AppDatabase.beers.getByStyle(style).map { beers =>
      Ok(views.html.beers(style, beers))
    }
  }
}
