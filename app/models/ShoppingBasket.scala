package models

import scala.collection.mutable.ArrayBuffer

case class ShoppingBasket(basket: ArrayBuffer[GameItem]) {}

object ShoppingBasket {

  val shoppingBasket = ArrayBuffer[GameItem]()

}