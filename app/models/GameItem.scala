package models

case class GameItem(id: String, name: String, imageUrl: String, description: String,
                    price: Double, minPlayers: Int, maxPlayers: Int, tags: List[String]) {}

object GameItem {

  val gamesList = List(
    GameItem("hive", "Hive", "hive.jpg", "Insect tile game", 20.0, 2, 2, List("twoPlayer", "strategy")),
    GameItem("scythe", "Scythe", "scythe.jpg", "Area control and resource management game", 70.0, 1, 5, List("onePlayer", "strategy")),
    GameItem("love-letter", "Love Letter", "loveLetter.jpg", "Fast playing card game", 12.0, 2, 4, List("family", "quick")),
    GameItem("celestia", "Celestia", "celestia.jpg", "Airship adventure game", 28.0, 2, 6, List("family", "strategy")),
    GameItem("agricola", "Agricola", "agricola.jpg", "17th century farming game", 60.0, 1, 5, List("onePlayer", "strategy"))
  )

  val nullItem = GameItem("missing", "missing", "blank.jpg", "no description", 0.0, 0, 0, List(""))

  def findGameById(gameName: String) = gamesList.find(_.id == gameName) match {
    case Some(game) => game
    case None => GameItem.nullItem
  }

}
