package controllers

import models.ShoppingBasket
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

  def plannedUpdates = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index("Planned technical updates. Please come back later."))
  }

}