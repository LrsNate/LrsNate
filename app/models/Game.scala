package models

import play.api.libs.json._
import play.api.libs.functional.syntax._

/**
 * Created by Nate on 31/10/15.
 */
case class Game(gameId: String, grid: Grid)

object Game {
  val reader: Reads[Game] = (
    (JsPath \ "game_id").read[String] and
    (JsPath \ "grid").read[Grid]
  )(Game.apply _)

  val writer: Writes[Game] = Writes { g =>
    Json.obj(
      "game_id" -> g.gameId,
      "grid" -> g.grid
    )
  }

  implicit val format: Format[Game] = Format(reader, writer)
}