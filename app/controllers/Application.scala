package controllers

import play.api.mvc._

class Application extends Controller {

  def index = Action {
    implicit request: Request[AnyContent] =>
      Ok(views.html.index("Hello world!"))
  }

  def indexRedirect = Action {
    implicit request: Request[AnyContent] =>
      Redirect("/index")
  }

  def plannedUpdates = Action {
    implicit request: Request[AnyContent] =>
      Ok("Planned technical updates. Please come back later.")
  }

  def page = Action {
    implicit request: Request[AnyContent] =>
      Ok(<ul><li>Hello world!</li><li>New page</li></ul>).as(HTML)
  }

  def displayGame(game: String) = Action {
    implicit request: Request[AnyContent] =>
      Ok(s"$game is not currently in stock. Please come back later.")
  }

}