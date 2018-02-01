package controllers

import models.{GameFilter, GameItem}
import play.api.mvc.{Action, AnyContent, Controller, Request}
import models.GameItem.gamesList

import scala.collection.mutable.ArrayBuffer

class Games extends Controller {

  val filter = GameFilter("", 1, 8, 0.0, 1000.0, ArrayBuffer())

  def games = Action { implicit request: Request[AnyContent] =>
    filter.tags.clear()
    Ok(views.html.games(gamesList, filter, 1))
  }

  // current default is 9 games per page
  def gamesPage(pgNo: Int) = Action { implicit request: Request[AnyContent] =>
    filter.tags.clear()
    Ok(views.html.games(gamesList, filter, pgNo))
  }

  def gamesFiltered = Action { implicit request: Request[AnyContent] =>
    val filters = request.body.asFormUrlEncoded.get
    var filteredList = gamesList

    val sortBy = filters("sortBy").head
    val minPlayers = filters("minPlayers").head.toInt
    val maxPlayers = filters("maxPlayers").head.toInt
    val minPrice = filters("minPrice").head.toDouble
    val maxPrice = filters("maxPrice").head.toDouble

    filteredList = gamesList.filter( game => game.maxPlayers >= minPlayers && game.minPlayers <= maxPlayers)
    filteredList = filteredList.filter(game => game.price >= minPrice && game.price <= maxPrice)

    val f = GameFilter("", minPlayers, maxPlayers, minPrice, maxPrice, filter.tags)

    sortBy match {
      case "A-Z" => filteredList = filteredList.sortBy(_.name)
      case "Z-A" => filteredList = filteredList.sortBy(_.name).reverse
      case "Z-A" => filteredList = filteredList.sortBy(_.name).reverse
      case "Z-A" => filteredList = filteredList.sortBy(_.name).reverse
    }


    Ok(views.html.games(filteredList, f, 1))
  }

  def gamesFilteredWithPgNo(pgNo: Int) = Action { implicit request: Request[AnyContent] =>
    val filters = request.body.asFormUrlEncoded.get
    var filteredList = gamesList

    val minPlayers = filters("minPlayers").head.toInt
    val maxPlayers = filters("maxPlayers").head.toInt
    val minPrice = filters("minPrice").head.toDouble
    val maxPrice = filters("maxPrice").head.toDouble

    filteredList = gamesList.filter( game => game.maxPlayers >= minPlayers && game.minPlayers <= maxPlayers)
    filteredList = filteredList.filter(game => game.price >= minPrice && game.price <= maxPrice)

    val f = GameFilter("", minPlayers, maxPlayers, minPrice, maxPrice, filter.tags)

    Ok(views.html.games(filteredList, f, 1))
  }

  def filterGamesByCategory(category: String) = {
    filter.tags.clear()
    filter.tags += category
    gamesFiltered
  }

  def gamesByCategory(category: String) = Action { implicit request: Request[AnyContent] =>
    filter.tags.clear()
    filter.tags += category
    Ok(views.html.games(gamesList, filter, 1))
  }

  def displayGame(gameName: String) = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.item(GameItem.findGameById(gameName)))
  }

  def searchGames() = Action { implicit request: Request[AnyContent] =>
    val searchTerm = request.body.asFormUrlEncoded.get("searchField").head
    val matchingGames = findGamesFromSearch(searchTerm)
    Ok(views.html.search(searchTerm, matchingGames))
  }

  def findGamesFromSearch(searchTerm: String): List[GameItem] = gamesList.filter(_.name.toLowerCase.contains(searchTerm.toLowerCase()))

}
