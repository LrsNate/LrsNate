package models

import org.scalatest.{FlatSpec, Matchers}
import play.api.libs.json.Json

/**
 * Created by Nate on 31/10/15.
 */
class MoveTests extends FlatSpec with Matchers {

  val move = Move("42", Mob("inftr", 1, 0), Mob("inftr", 0, 1))

  val jsobj = Json.obj(
    "game_id" -> "42",
    "from" -> Json.obj(
      "kind" -> "inftr",
      "x" -> 1,
      "y" -> 0
    ),
    "to" -> Json.obj(
      "kind" -> "inftr",
      "x" -> 0,
      "y" -> 1
    )
  )

  "A Move" should "reject non-matching units" in {
    an[IllegalArgumentException] should be thrownBy {
      Move("42", Mob("inftr", 1, 0), Mob("mech", 1, 0))
    }
  }

  it can "be read from a Json object" in {
    jsobj.as[Move] shouldBe move
  }

  it can "reject invalid Json input" in {
    Json.arr().asOpt[Move] shouldBe None
  }

  it can "be converted to a Json object" in {
    Json.toJson(move) shouldBe jsobj
  }
}
