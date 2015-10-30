package controllers

import models.{Mob, Cell, Grid}
import play.api.libs.json.Json
import play.api.mvc.{Controller, Action}

/**
 * Created by Nate on 18/10/15.
 */
trait AdvanceWars extends Controller {
  def index = Action(Ok(views.html.aw_index()))

  def defaultMap = Action {
    val row = Seq(
      Cell("plain"),
      Cell("plain"),
      Cell("plain"),
      Cell("plain"),
      Cell("plain")
    )
    val map = Seq(row, row, row, row)
    val units = Seq(Mob("inftr", 1, 1))
    Ok(Json.toJson(Grid(map, units)))
  }
}

object AdvanceWars extends Controller with AdvanceWars