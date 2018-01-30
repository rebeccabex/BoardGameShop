package controllers

import play.api.mvc.{Action, AnyContent, Controller, Request}

class Games extends Controller {

  val gamesList = Array(
    ("Hive", "Insect tile game", "hive.jpg", 20.0, Array("twoPlayer", "strategy")),
    ("Scythe", "Area control and resource management game", "scythe.jpg", 70.0, Array("onePlayer", "strategy")),
    ("Love Letter", "Fast playing card game", "loveLetter.jpg", 12.0, Array("family", "quick")),
    ("Celestia", "Airship adventure game", "celestia.jpg", 28.0, Array("family", "strategy")),
    ("Agricola", "17th Farming game", "agricola.jpg", 60.0, Array("onePlayer", "strategy"))
  )

  def games = Action {
    implicit request: Request[AnyContent] =>
      Ok(views.html.games(gamesList, "all"))
  }

  def gamesByCategory(category: String) = Action {
    implicit request: Request[AnyContent] =>
      Ok(views.html.games(gamesList, category))
  }

  def displayGame(gameName: String) = Action {
    implicit request: Request[AnyContent] =>
      Ok(views.html.item(findGameByName(gameName)))
  }

  def findGameByName(gameName: String) =
    gamesList.find(_._1 == gameName) match {
      case Some(game) => game
      case None => ("missing", "no description", "blank.jpg", 0.0, Array(""))
    }

}
