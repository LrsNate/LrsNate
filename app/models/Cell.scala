package models

import play.api.libs.json._

/**
 * Created by Nate on 29/10/15.
 */
case class Cell(kind: String)

object Cell {
  val reader: Reads[Cell] = (JsPath \ "kind").read[String].map(Cell(_))

  val writer = Writes[Cell] { c => Json.obj("kind" -> c.kind) }

  implicit val format: Format[Cell] = Format(reader, writer)
}