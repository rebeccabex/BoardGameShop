package models

case class gameItem(id: String, name: String, imageUrl: String, shortDescr: String, longDescr: String, price: Double, tags: List[String]) {}
