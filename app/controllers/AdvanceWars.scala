package controllers

import play.api.libs.json.Json
import play.api.mvc.Action

/**
 * Created by Nate on 18/10/15.
 */
object AdvanceWars extends RESTController {
  def index = Action(Ok(views.html.aw_index()))

  def defaultMap = Action {
    val row = Json.arr(
      Json.obj("kind" -> "plain"),
      Json.obj("kind" -> "plain"),
      Json.obj("kind" -> "plain"),
      Json.obj("kind" -> "plain"),
      Json.obj("kind" -> "plain")
    )
    val map = Json.arr(row, row, row, row)
    val units = Json.arr(
      Json.obj(
        "kind" -> "inftr",
        "x" -> 1,
        "y" -> 1
      )
    )
    Ok(Json.obj(
      "grid" -> map,
      "units" -> units
    ))
  }
}
