package models

import scala.collection.mutable.ArrayBuffer

case class ShoppingBasket(basket: ArrayBuffer[GameItem]) {}
//
//object ShoppingBasket {
//  def apply(basket: ArrayBuffer[GameItem]): ShoppingBasket = new ShoppingBasket(basket)
//
//}