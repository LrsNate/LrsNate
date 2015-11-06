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

  val rows = cells.length
  val cols = if (rows == 0) 0 else cells.head.length

  def moveUnit(move: Move): Grid = {
    def isOriginUnit(m: Mob) = {
      m.kind == move.from.kind &&
      m.x == move.from.x &&
      m.y == move.from.y
    }
    if (!(units contains move.from)) {
      throw new NoSuchElementException("No such unit")
    }
    if (move.to.x < 0 || move.to.x >= cols || move.to.y < 0 || move.to.y >= cols) {
      throw new Exception("Move out of bounds")
    }
    if (units exists { p => p.x == move.to.x && p.y == move.to.y }) {
      throw new Exception("Unit collision")
    }
    val updatedUnits = units map { m =>
      if (isOriginUnit(m)) Mob(m.kind, move.to.x, move.to.y)
      else m
    }
    Grid(cells, updatedUnits)
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