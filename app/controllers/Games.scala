package controllers

import models.gameItem
import play.api.mvc.{Action, AnyContent, Controller, Request}

class Games extends Controller {

  val nullItem = gameItem("missing", "missing", "blank.jpg", "no description", 0.0, 0, 0, List(""))
  val gamesList = List(
    gameItem("hive", "Hive", "hive.jpg", "Insect tile game", 20.0, 2, 2, List("twoPlayer", "strategy")),
    gameItem("scythe", "Scythe", "scythe.jpg", "Area control and resource management game", 70.0, 1, 5, List("onePlayer", "strategy")),
    gameItem("love-letter", "Love Letter", "loveLetter.jpg", "Fast playing card game", 12.0, 2, 4, List("family", "quick")),
    gameItem("celestia", "Celestia", "celestia.jpg", "Airship adventure game", 28.0, 2, 6, List("family", "strategy")),
    gameItem("agricola", "Agricola", "agricola.jpg", "17th Farming game", 60.0, 1, 5, List("onePlayer", "strategy"))
  )

  def games = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.games(gamesList, "all"))
  }

  def gamesByCategory(category: String) = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.games(gamesList, category))
  }

  def displayGame(gameName: String) = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.item(findGameByName(gameName)))
  }

  def findGameByName(gameName: String) = gamesList.find(_.id == gameName) match {
      case Some(game) => game
      case None => nullItem
    }

  def findGamesFromSearch(searchTerm: String) = gamesList.filter(_.name.toLowerCase.contains(searchTerm.toLowerCase()))

  def searchGames() = Action { implicit request: Request[AnyContent] =>
    val searchTerm = request.body.asFormUrlEncoded.get("searchField").head
    val matchingGames = findGamesFromSearch(searchTerm)
    Ok(views.html.search(searchTerm, matchingGames))
  }

}
