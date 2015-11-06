package controllers

import mocks.GameDaoMock
import models.{Mob, Move, Game}
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.time.{Seconds, Span, Millis}
import org.scalatest.{Matchers, FlatSpec}
import play.api.libs.json.Json
import play.api.test.{FakeHeaders, Helpers, FakeRequest}
import play.api.test.Helpers.defaultAwaitTimeout

import scala.concurrent.Future

/**
 * Created by Nate on 30/10/15.
 */
class AdvanceWarsTests extends FlatSpec with Matchers with ScalaFutures {

  implicit val defaultPatience =
    PatienceConfig(timeout = Span(5, Seconds), interval = Span(500, Millis))

  class AdvanceWarsTestCtrl extends AdvanceWars(new GameDaoMock)

  val controller = new AdvanceWarsTestCtrl

  "The game state controller" should "return a valid game state" in {
    val result = controller.getGameState("1").apply(FakeRequest())

    whenReady(result) { res =>
      res.header.status shouldBe 200
      val body = Helpers.contentAsJson(Future.successful(res))
      body.asOpt[Game] shouldBe defined
    }
  }

  it should "reject an unknown game id" in {
    val result = controller.getGameState("42").apply(FakeRequest())
    whenReady(result) { res =>
      res.header.status shouldBe 404
    }
  }

  "The unit move controller" should "acknowledge a valid request" in {
    val move = Move("1", Mob("inftr", 0, 0), Mob("inftr", 0, 1))
    val req = FakeRequest("GET", "", FakeHeaders(), Json.toJson(move))
    val result = controller.moveUnit.apply(req)
    whenReady(result) { res =>
      res.header.status shouldBe 200
    }
  }

  it should "reject a malformed request" in {
    val req = FakeRequest("GET", "", FakeHeaders(), Json.obj())
    val result = controller.moveUnit.apply(req)
    whenReady(result) { res =>
      res.header.status shouldBe 400
    }
  }

  it should "reject an unknown game id" in {
    val move = Move("42", Mob("inftr", 0, 0), Mob("inftr", 0, 1))
    val req = FakeRequest("GET", "", FakeHeaders(), Json.toJson(move))
    val result = controller.moveUnit.apply(req)
    whenReady(result) { res =>
      res.header.status shouldBe 404
    }
  }

  it should "reject a unit collision" in {
    val move = Move("1", Mob("inftr", 0, 0), Mob("inftr", 0, 2))
    val req = FakeRequest("GET", "", FakeHeaders(), Json.toJson(move))
    val result = controller.moveUnit.apply(req)
    whenReady(result) { res =>
      res.header.status shouldBe 403
    }
  }

  it should "reject a unit move out of the grid bounds" in {
    val move = Move("1", Mob("inftr", 0, 0), Mob("inftr", 0, 3))
    val req = FakeRequest("GET", "", FakeHeaders(), Json.toJson(move))
    val result = controller.moveUnit.apply(req)
    whenReady(result) { res =>
      res.header.status shouldBe 403
    }
  }
}
