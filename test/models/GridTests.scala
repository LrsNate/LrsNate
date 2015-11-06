package models

import org.scalatest.{Matchers, FlatSpec}
import play.api.libs.json.Json

/**
 * Created by Nate on 30/10/15.
 */
class GridTests extends FlatSpec with Matchers {

  val cells = {
    val cell = Cell("plain")
    val row = Seq(cell, cell, cell)
    Seq(row, row)
  }

  val units = Seq(Mob("inftr", 0, 0), Mob("inftr", 0, 2))

  val jsobj = {
    val cell = Json.obj("kind" -> "plain")
    val row = Json.arr(cell, cell, cell)
    val grid = Json.arr(row, row)
    val units = Json.arr(
      Json.obj("kind" -> "inftr", "x" -> 0, "y" -> 0),
      Json.obj("kind" -> "inftr", "x" -> 0, "y" -> 2)
    )
    Json.obj("grid" -> grid, "units" -> units)
  }

  "A Grid" can "be built from Scala objects" in {
    Grid(cells, units)
  }

  it should "reject irregular fields" in {
    an[IllegalArgumentException] should be thrownBy Grid(cells :+ Seq(Cell("plain")), units)
  }

  it can "be read from a Json object" in {
    jsobj.asOpt[Grid] shouldBe Some(Grid(cells, units))
  }

  it should "reject invalid Json input" in {
    Json.arr().asOpt[Grid] shouldBe None
  }

  it can "be converted to a Json object" in {
    Json.toJson(Grid(cells, units)) shouldBe jsobj
  }

  it can "apply a unit move" in {
    val res = Grid(cells, units) moveUnit Move("", Mob("inftr", 0, 0), Mob("inftr", 0, 1))
    res shouldBe Grid(cells, Seq(Mob("inftr", 0, 1), Mob("inftr", 0, 2)))
  }

  it should "reject a unit collision" in {
      an[Exception] should be thrownBy Grid(cells, units).moveUnit(
        Move("", Mob("inftr", 0, 0), Mob("inftr", 0, 2))
      )
  }

  it should "reject a unit move out of the grid bounds" in {
    an[Exception] should be thrownBy Grid(cells, units).moveUnit(
      Move("", Mob("inftr", 0, 0), Mob("inftr", 0, 16))
    )
  }

  it should "reject a non-existing unit" in {
    a[NoSuchElementException] should be thrownBy Grid(cells, units).moveUnit(
      Move("", Mob("inftr", 0, 1), Mob("inftr", 0, 0))
    )
  }
}
