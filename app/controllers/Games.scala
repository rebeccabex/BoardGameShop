package controllers

import models.gameItem
import play.api.mvc.{Action, AnyContent, Controller, Request}

class Games extends Controller {

  val gamesList = List(
    gameItem("hive", "Hive", "hive.jpg", "Insect tile game", "Insect tile game", 20.0, List("twoPlayer", "strategy")),
    gameItem("scythe", "Scythe", "scythe.jpg", "Area control and resource management game", "Area control and resource management game", 70.0, List("onePlayer", "strategy")),
    gameItem("love-letter", "Love Letter", "loveLetter.jpg", "Fast playing card game", "Fast playing card game", 12.0, List("family", "quick")),
    gameItem("celestia", "Celestia", "celestia.jpg", "Airship adventure game", "Airship adventure game", 28.0, List("family", "strategy")),
    gameItem("agricola", "Agricola", "agricola.jpg", "17th Farming game", "17th Farming game", 60.0, List("onePlayer", "strategy"))
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

  def findGameByName(gameName: String) =
    gamesList.find(_.id == gameName) match {
      case Some(game) => game
      case None => gameItem("missing", "missing", "blank.jpg", "no description", "no description", 0.0, List(""))
    }

  def searchGames() = Action { implicit request: Request[AnyContent] =>
    val searchTerm = request.body.asFormUrlEncoded.get("searchField").head
    Ok(views.html.search(searchTerm))
  }

}
