package controllers

import models.AppDatabaseProvider
import play.api.mvc._

import scala.concurrent.ExecutionContext.Implicits.global

object Application extends Controller with AppDatabaseProvider {

  def index: Action[AnyContent] = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def beers(style: String): Action[AnyContent] = Action.async {
    database.beers.getByStyle(style).map { beers =>
      Ok(views.html.beers(style, beers))
    }
  }

}