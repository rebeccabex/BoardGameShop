package controllers

import models.GameItem
import models.ShoppingBasket.shoppingBasket
import play.api.mvc.{Action, AnyContent, Controller, Request}

class Basket extends Controller {

  def basket = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.basket(shoppingBasket))
  }

  def addItemToBasket(item: String) = Action { implicit request: Request[AnyContent] =>
    shoppingBasket += GameItem.findGameById(item)
    val path = request.headers("referer")
    Redirect(path)
  }

  def removeItemFromBasket(item: String) = Action { implicit request: Request[AnyContent] =>
    shoppingBasket -= GameItem.findGameById(item)
    Redirect(routes.Basket.basket())
  }

  def removeAllFromBasket() = Action { implicit request: Request[AnyContent] =>
    shoppingBasket.clear()
    Redirect(routes.Basket.basket())
  }

  def pay = Action { implicit request: Request[AnyContent] =>
    shoppingBasket.clear()
    Ok(views.html.blankPage("You have paid for your items"))
  }

}
