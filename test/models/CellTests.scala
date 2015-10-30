package models

import org.scalatest.{Matchers, FlatSpec}
import play.api.libs.json.Json

/**
 * Created by Nate on 29/10/15.
 */
class CellTests extends FlatSpec with Matchers {

  val cell = Cell("plain")
  val jsobj = Json.obj("kind" -> "plain")

  "A Cell" can "be read from a Json object" in {
    jsobj.as[Cell] shouldBe cell
  }

  it should "reject a malformed Json input" in {
    Json.arr().asOpt[Cell] shouldBe None
  }

  it can "be converted to a Json object" in {
    Json.toJson(cell) shouldBe jsobj
  }
}
