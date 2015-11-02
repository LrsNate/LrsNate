package controllers

import models.{Game, Mob, Cell, Grid}
import play.api.libs.json.Json
import play.api.mvc.{Controller, Action}

/**
 * Created by Nate on 18/10/15.
 */
trait AdvanceWars extends Controller {

  val row = Seq(
    Cell("plain"),
    Cell("plain"),
    Cell("plain"),
    Cell("plain"),
    Cell("plain")
  )
  val map = Seq(row, row, row, row)
  val units = Seq(Mob("inftr", 1, 1))

  def index = Action(Ok(views.html.aw_index()))

  def getGameState(gameId: String) = Action {
    gameId match {
      case "1" => Ok(Json.toJson(Game("1", Grid(map, units))))
      case _ => NotFound
    }
  }

  def moveUnit = Action(parse.json) { body =>
    NotImplemented
  }
}

object AdvanceWars extends Controller with AdvanceWars