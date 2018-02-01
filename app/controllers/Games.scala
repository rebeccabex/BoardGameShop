package controllers

import models.{GameFilter, GameItem}
import play.api.mvc.{Action, AnyContent, Controller, Request}

import scala.collection.mutable.ArrayBuffer

class Games extends Controller {

  val nullItem = GameItem("missing", "missing", "blank.jpg", "no description", 0.0, 0, 0, List(""))
  val filter = GameFilter("", 1, 8, 0.0, 1000.0, ArrayBuffer())
  val gamesList = List(
    GameItem("hive", "Hive", "hive.jpg", "Insect tile game", 20.0, 2, 2, List("twoPlayer", "strategy")),
    GameItem("scythe", "Scythe", "scythe.jpg", "Area control and resource management game", 70.0, 1, 5, List("onePlayer", "strategy")),
    GameItem("love-letter", "Love Letter", "loveLetter.jpg", "Fast playing card game", 12.0, 2, 4, List("family", "quick")),
    GameItem("celestia", "Celestia", "celestia.jpg", "Airship adventure game", 28.0, 2, 6, List("family", "strategy")),
    GameItem("agricola", "Agricola", "agricola.jpg", "17th century farming game", 60.0, 1, 5, List("onePlayer", "strategy"))
  )

  def games = Action { implicit request: Request[AnyContent] =>
    filter.tags.clear()
    println(request)

    Ok(views.html.games(gamesList, filter))
  }

  def gamesFiltered = Action { implicit request: Request[AnyContent] =>
    val filters = request.body.asFormUrlEncoded.get
    var filteredList = gamesList

    val minPlayers = filters("minPlayers").head.toInt
    val maxPlayers = filters("maxPlayers").head.toInt
    val minPrice = filters("minPrice").head.toDouble
    val maxPrice = filters("maxPrice").head.toDouble

    filteredList = gamesList.filter( game => game.maxPlayers >= minPlayers && game.minPlayers <= maxPlayers)
    filteredList = filteredList.filter(game => game.price >= minPrice && game.price <= maxPrice)

    val f = GameFilter("", minPlayers, maxPlayers, minPrice, maxPrice, filter.tags)

    Ok(views.html.games(filteredList, f))
  }

  def filterGamesByCategory(category: String) = {
    filter.tags.clear()
    filter.tags += category
    gamesFiltered
  }

  def gamesByCategory(category: String) = Action { implicit request: Request[AnyContent] =>
    filter.tags.clear()
    filter.tags += category
    Ok(views.html.games(gamesList, filter))
  }

  def displayGame(gameName: String) = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.item(findGameById(gameName)))
  }

  def findGameById(gameName: String) = gamesList.find(_.id == gameName) match {
      case Some(game) => game
      case None => nullItem
    }

  def findGamesFromSearch(searchTerm: String): List[GameItem] = gamesList.filter(_.name.toLowerCase.contains(searchTerm.toLowerCase()))

  def searchGames() = Action { implicit request: Request[AnyContent] =>
    val searchTerm = request.body.asFormUrlEncoded.get("searchField").head
    val matchingGames = findGamesFromSearch(searchTerm)
    Ok(views.html.search(searchTerm, matchingGames))
  }

  val shoppingBasket = ArrayBuffer[GameItem]()

  def addItemToBasket(item: String) = Action { implicit request: Request[AnyContent] =>
    shoppingBasket += findGameById(item)
    Ok(views.html.basket(shoppingBasket))
  }

  def basket = Action {
    implicit request: Request[AnyContent] =>
      Ok(views.html.basket(shoppingBasket))
  }

}
