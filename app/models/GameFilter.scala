package models

import scala.collection.mutable.ArrayBuffer

case class GameFilter(partialName: String, minPlayers: Int, maxPlayers: Int, minPrice: Double, maxPrice: Double, tags: ArrayBuffer[String])


