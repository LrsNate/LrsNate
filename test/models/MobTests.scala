package models

import org.scalatest.{FlatSpec, Matchers}
import play.api.libs.json.Json

/**
 * Created by Nate on 30/10/15.
 */
class MobTests extends FlatSpec with Matchers {

  val mob = Mob("inftr", 1, 0)
  val jsobj = Json.obj("kind" -> "inftr", "x" -> 1, "y" -> 0)

  "A Mob" can "be read from a Json object" in {
    jsobj.asOpt[Mob] shouldBe Some(mob)
  }

  it should "reject incomplete objects" in {
    Json.obj("kind" -> "inftr").asOpt[Mob] shouldBe None
  }

  it should "reject a malformed Json input" in {
    Json.arr().asOpt[Mob] shouldBe None
  }

  it can "be converted to a Json object" in {
    Json.toJson(mob) shouldBe jsobj
  }
}
