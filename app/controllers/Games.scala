package controllers

import play.api.mvc.{Action, AnyContent, Controller, Request}

class Games extends Controller {

  val gamesList = Array(
    ("Hive", "Insect tile game", "hive.jpg", 20.0, Array("Two Player", "Strategy")),
    ("Scythe", "Area control and resource management game", "scythe.jpg", 70.0, Array("One Player", "Strategy")),
    ("Love Letter", "Fast playing card game", "loveLetter.jpg", 12.0, Array("Family", "Quick")),
    ("Celestia", "Airship adventure game", "celestia.jpg", 28.0, Array("Family", "Strategy")),
    ("Agricola", "17th Farming game", "agricola.jpg", 60.0, Array("One Player", "Strategy"))
  )

  def games = Action {
    implicit request: Request[AnyContent] =>
      Ok(views.html.games(gamesList))
  }


  def displayGame(game: String) = Action {
    implicit request: Request[AnyContent] =>
      Ok(views.html.blank(s"$game is not currently in stock. Please come back later."))
  }

}
