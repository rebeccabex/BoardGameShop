package models

case class gameItem(id: String, name: String, imageUrl: String, description: String,
                    price: Double, minPlayers: Int, maxPlayers: Int, tags: List[String]) {}
