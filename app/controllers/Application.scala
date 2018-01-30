package controllers

import play.api.mvc._

class Application extends Controller {

  def index = Action {
    implicit request: Request[AnyContent] =>
      Ok(views.html.index("Welcome!"))
  }

  def basket = Action {
    implicit request: Request[AnyContent] =>
      Ok(views.html.basket("Your items"))
  }

  def indexRedirect = Action {
    implicit request: Request[AnyContent] =>
      Redirect("/index")
  }

  def games = Action {
    implicit request: Request[AnyContent] =>
      Ok(views.html.games("List of games"))
  }

  def page = Action {
    implicit request: Request[AnyContent] =>
      Ok(views.html.about("Hello world!"))
  }

  def displayGame(game: String) = Action {
    implicit request: Request[AnyContent] =>
      Ok(s"$game is not currently in stock. Please come back later.")
  }

  def plannedUpdates = Action {
    implicit request: Request[AnyContent] =>
      Ok(views.html.index("Planned technical updates. Please come back later."))
  }

}