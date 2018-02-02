package controllers

import models.{GameItem, ShoppingBasket}
import play.api.mvc._

class Application extends Controller {

  def index = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index("Welcome!", ShoppingBasket.shoppingBasket.toList, GameItem.gamesList))
  }

  def indexRedirect = Action { implicit request: Request[AnyContent] =>
    Redirect(routes.Application.index())
  }

  def page = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.about("Hello world!", ShoppingBasket.shoppingBasket.toList))
  }

  def plannedUpdates = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index("Planned technical updates. Please come back later.", ShoppingBasket.shoppingBasket.toList, GameItem.gamesList))
  }

}