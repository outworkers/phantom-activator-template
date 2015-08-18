package controllers

import models.AppDatabase
import play.api.mvc._

import scala.concurrent.ExecutionContext.Implicits.global

object Application extends Controller {

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def beers(style: String) = Action.async {
    AppDatabase.beers.getByStyle(style).map { beers =>
      Ok(views.html.beers(style, beers))
    }
  }

}