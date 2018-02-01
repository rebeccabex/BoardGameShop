package models

import scala.collection.mutable.ArrayBuffer

case class GameItem(id: String, name: String, imageUrl: String, description: String,
                    price: Double, minPlayers: Int, maxPlayers: Int, tags: List[String]) {}

case class GameFilter(partialName: String, minPlayers: Int, maxPlayers: Int, minPrice: Double, maxPrice: Double, tags: ArrayBuffer[String])

