package models

import play.api.libs.json._
import play.api.libs.functional.syntax._

/**
 * Created by Nate on 29/10/15.
 */
case class Mob(kind: String, x: Int, y: Int)

object Mob {
  val reader: Reads[Mob] = (
    (JsPath \ "kind").read[String] and
    (JsPath \ "x").read[Int] and
    (JsPath \ "y").read[Int]
    )(Mob.apply _)

  val writer = Writes[Mob] { c =>
    Json.obj(
      "kind" -> c.kind,
      "x" -> c.x,
      "y" -> c.y
    )
  }

  implicit val format: Format[Mob] = Format(reader, writer)
}

