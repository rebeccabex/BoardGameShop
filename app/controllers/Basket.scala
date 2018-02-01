package controllers

import models.GameItem
import play.api.mvc.{Action, AnyContent, Controller, Request}
import models.ShoppingBasket.shoppingBasket

class Basket extends Controller {

  def basket = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.basket(shoppingBasket))
  }

  def addItemToBasket(item: String) = Action { implicit request: Request[AnyContent] =>
    shoppingBasket += GameItem.findGameById(item)
    Redirect(routes.Basket.basket())
  }

  def removeItemFromBasket(item: String) = Action { implicit request: Request[AnyContent] =>
    shoppingBasket -= GameItem.findGameById(item)
    Redirect(routes.Basket.basket())
  }

  def removeAllFromBasket() = Action { implicit request: Request[AnyContent] =>
    shoppingBasket.clear()
    Redirect(routes.Basket.basket())
  }

}
