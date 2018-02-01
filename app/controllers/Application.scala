package controllers

import play.api.mvc._

class Application extends Controller {

  def index = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index("Welcome!"))
  }

  def indexRedirect = Action { implicit request: Request[AnyContent] =>
    Redirect("/index")
  }

  def page = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.about("Hello world!"))
  }

  def pay = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.blankPage("You have paid for your items"))
  }

  def plannedUpdates = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index("Planned technical updates. Please come back later."))
  }

}