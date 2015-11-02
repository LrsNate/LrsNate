package models

import org.scalatest.{FlatSpec, Matchers}
import play.api.libs.json.{JsError, JsSuccess, Json}

/**
 * Created by Nate on 31/10/15.
 */
class GameTests extends FlatSpec with Matchers {

  val cells = {
    val cell = Cell("plain")
    val row = Seq(cell, cell, cell)
    Seq(row, row)
  }

  val units = Seq(Mob("inftr", 1, 0))

  val game = Game("42", Grid(cells, units))

  val jsobj = {
    val cell = Json.obj("kind" -> "plain")
    val row = Json.arr(cell, cell, cell)
    val units = Json.arr(Json.obj("kind" -> "inftr", "x" -> 1, "y" -> 0))
    val grid = Json.obj("grid" -> Json.arr(row, row), "units" -> units)
    Json.obj("game_id" -> "42", "grid" -> grid)
  }

  "A Game" can "be read from a Json object" in {
    jsobj.asOpt[Game] shouldBe Some(game)
  }

  it should "reject invalid Json input" in {
    Json.arr().asOpt[Game] shouldBe None
  }

  it can "be converted to a Json object" in {
    Json.toJson(game) shouldBe jsobj
  }
}
