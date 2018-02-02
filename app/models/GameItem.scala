package models

case class GameItem(id: String, name: String, imageUrl: String, description: String,
                    price: Double, minPlayers: Int, maxPlayers: Int, tags: List[String]) {}

object GameItem {

  val gamesList = List(
    GameItem("hive", "Hive", "hive.jpg", "Insect tile game", 20.0, 2, 2, List("twoPlayer", "strategy")),
    GameItem("scythe", "Scythe", "scythe.jpg", "Area control and resource management game", 70.0, 1, 5, List("onePlayer", "strategy")),
    GameItem("love-letter", "Love Letter", "loveLetter.jpg", "Fast playing card game", 12.0, 2, 4, List("family", "quick", "card")),
    GameItem("celestia", "Celestia", "celestia.jpg", "Airship adventure game", 28.0, 2, 6, List("family", "strategy")),
    GameItem("agricola", "Agricola", "agricola.jpg", "17th century farming game", 60.0, 1, 5, List("onePlayer", "strategy")),
    GameItem("sushi-go", "Sushi Go", "sushiGo.jpg", "Fun card drafting game", 15.0, 2, 5, List("family", "party", "card")),
    GameItem("codenames", "Codenames", "codenames.jpg", "Word-based spy game", 18.5, 2, 10, List("party", "card", "team")),
    GameItem("forbidden-island", "Forbidden Island", "forbiddenIsland.jpg", "Co-operative game set on sinking island", 22.0, 2, 4, List("family", "cooperative")),
    GameItem("forbidden-desert", "Forbidden Desert", "forbiddenDesert.jpg", "Co-operative game set in the desert", 25.0, 2, 4, List("family", "cooperative")),
    GameItem("zombie-dice", "Zombie Dice", "zombieDice.jpg", "Quick playing brain-eating zombie game", 10.0, 2, 5, List("quick", "party")),
    GameItem("dixit", "Dixit", "dixit.jpg", "Storytelling card game", 28.0, 3, 6, List("family", "card", "party")),
    GameItem("machi-koro", "Machi Koro", "machiKoro.jpg", "City building card game", 24.0, 2, 4, List("card", "family", "quick")),
    GameItem("scythe-invaders-from-afar", "Scythe: Invaders From Afar", "scytheInvaders.jpg", "Expansion for Scythe with new factions", 28.0, 1, 7, List("expansion", "strategy", "onePlayer")),
    GameItem("scythe-wind-gambit", "Scythe: Wind Gambit", "scytheWind.jpg", "Expansion for Scythe with new modes of play", 24.0, 1, 7, List("expansion", "strategy", "onePlayer")),
    GameItem("machi-koro-millionaires-row", "Machi Koro: Millionaire's Row", "machiKoroMillionaire.jpg", "Expansion for Machi Koro with new cards", 15.0, 2, 4, List("family", "card", "expansion", "quick"))
  )

  val nullItem = GameItem("missing", "missing", "blank.jpg", "no description", 0.0, 0, 0, List(""))

  def findGameById(gameName: String): GameItem = gamesList.find(_.id == gameName) match {
    case Some(game) => game
    case None => GameItem.nullItem
  }

}
