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

  val units = Seq(Mob("inftr", 1, 0))

  val jsobj = {
    val cell = Json.obj("kind" -> "plain")
    val row = Json.arr(cell, cell, cell)
    val grid = Json.arr(row, row)
    val units = Json.arr(Json.obj("kind" -> "inftr", "x" -> 1, "y" -> 0))
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
}
