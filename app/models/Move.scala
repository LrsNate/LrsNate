package models

import play.api.libs.json._
import play.api.libs.functional.syntax._

/**
 * Created by Nate on 30/10/15.
 */
case class Move(gameId: String, from: Mob, to: Mob) {
  if (from.kind != to.kind) {
    throw new IllegalArgumentException("Bad unit type")
  }
}

object Move {
  val reader: Reads[Move] = (
      (JsPath \ "game_id").read[String] and
      (JsPath \ "from").read[Mob] and
      (JsPath \ "to").read[Mob]
    )(Move.apply _)

  val writer: Writes[Move] = Writes { m =>
    Json.obj(
      "game_id" -> m.gameId,
      "from" -> m.from,
      "to" -> m.to
    )
  }

  implicit val format: Format[Move] = Format(reader, writer)
}