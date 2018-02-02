package controllers

import models.{GameFilter, GameItem}
import play.api.mvc.{Action, AnyContent, Controller, Request}
import models.GameItem.gamesList

import scala.collection.mutable.ArrayBuffer

class Games extends Controller {

  val filter = GameFilter("", 1, 10, 0.0, 1000.0, ArrayBuffer(), 1, "A-Z")
  var minPlayers = 1
  var maxPlayers = 10
  var minPrice = 0.0
  var maxPrice = 1000.0
  var tags = ArrayBuffer[String]()
  var pgNo = 1
  var sortBy = "A-Z"

  def games = Action { implicit request: Request[AnyContent] =>
    resetFilter()
    val f = currentFilter()
    Ok(views.html.games(paginateGames(sortGames(filterGames(gamesList, f), sortBy)), f))
  }

  // current default is 9 games per page
  def paginateGames(gameList: List[GameItem]): List[List[GameItem]] = gameList.grouped(9).toList

  def gamesPage(newPgNo: Int) = Action { implicit request: Request[AnyContent] =>
    val sortedGames = sortGames(gamesList, currentFilter().sortBy)

    val f = GameFilter("", minPlayers, maxPlayers, minPrice, maxPrice, tags, newPgNo, sortBy)

    Ok(views.html.games(paginateGames(sortedGames), f))
  }

  def gamesFiltered: Action[AnyContent] = gamesFilteredWithPgNo(1)

  def gamesFilteredWithPgNo(newPgNo: Int) = Action { implicit request: Request[AnyContent] =>
    val filters = request.body.asFormUrlEncoded.get
    var filteredList = gamesList

    sortBy = filters("sortBy").head
    minPlayers = filters("minPlayers").head.toInt
    maxPlayers = filters("maxPlayers").head.toInt
    minPrice = filters("minPrice").head.toDouble
    maxPrice = filters("maxPrice").head.toDouble

    filteredList = gamesList.filter( game => game.maxPlayers >= minPlayers && game.minPlayers <= maxPlayers)
    filteredList = filteredList.filter(game => game.price >= minPrice && game.price <= maxPrice)

    filteredList = sortGames(filteredList, sortBy)

    val f = GameFilter("", minPlayers, maxPlayers, minPrice, maxPrice, filter.tags, newPgNo, sortBy)

    Ok(views.html.games(paginateGames(filteredList), f))
  }

  def filterGames(games: List[GameItem], f: GameFilter): List[GameItem] = {
    val filteredList = games.filter(game => game.maxPlayers >= f.minPlayers && game.minPlayers <= f.maxPlayers)
    filteredList.filter(game => game.price >= f.minPrice && game.price <= f.maxPrice)
  }

  def sortGames(games: List[GameItem], sortBy: String): List[GameItem] = {
    var sortedGames = games
    sortBy match {
      case "A-Z" => sortedGames = games.sortBy(_.name)
      case "Z-A" => sortedGames = games.sortBy(_.name).reverse
      case "Price: Low to High" => sortedGames = games.sortBy(_.price)
      case "Price: High to Low" => sortedGames = games.sortBy(_.price).reverse
    }
    sortedGames
  }

  def filterGamesByCategory(category: String): Action[AnyContent] = {
    filter.tags.clear()
    filter.tags += category
    gamesFiltered
  }

  def gamesByCategory(category: String) = Action { implicit request: Request[AnyContent] =>
    filter.tags.clear()
    filter.tags += category
    Ok(views.html.games(paginateGames(gamesList), filter))
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

  def currentFilter(): GameFilter = GameFilter("", minPlayers, maxPlayers, minPrice, maxPrice, tags, pgNo, sortBy)

  def resetFilter(): GameFilter = {
    minPlayers = 1
    maxPlayers = 10
    minPrice = 0.0
    maxPrice = 1000.0
    tags.clear()
    pgNo = 1
    sortBy = "A-Z"
    currentFilter()
  }

}
