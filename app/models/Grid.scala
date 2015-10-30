package models

import play.api.libs.json._
import Cell._

import play.api.libs.functional.syntax._

/**
 * Created by Nate on 29/10/15.
 */
case class Grid(private val _cells: Seq[Seq[Cell]], units: Seq[Mob]) {
  val cells = _cells match {
    case Seq() => Seq()
    case _ =>
      if (_cells forall { p => p.length == _cells.head.length }) _cells
      else throw new IllegalArgumentException("Irregular map")
  }
}

object Grid {
  val reader: Reads[Grid] = (
    (JsPath \ "grid").read[Seq[Seq[Cell]]] and
      (JsPath \ "units").read[Seq[Mob]]
    )(Grid.apply _)

  val writer: Writes[Grid] = Writes[Grid] { g =>
    Json.obj(
      "grid" -> g.cells,
      "units" -> g.units
    )
  }

  implicit val format: Format[Grid] = Format(reader, writer)
}