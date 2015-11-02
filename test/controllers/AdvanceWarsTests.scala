package controllers

import mocks.GameDaoMock
import models.Game
import org.scalatest.{Matchers, FlatSpec}
import play.api.test.{Helpers, FakeRequest}
import play.api.test.Helpers.defaultAwaitTimeout

/**
 * Created by Nate on 30/10/15.
 */
class AdvanceWarsTests extends FlatSpec with Matchers {

  class AdvanceWarsTestCtrl extends  AdvanceWars(new GameDaoMock)

  val controller = new AdvanceWarsTestCtrl

  "The game state controller" should "return a valid game state" in {
    val result = controller.getGameState("1").apply(FakeRequest())
    val body = Helpers.contentAsJson(result)
    body.asOpt[Game] shouldBe defined
  }
}
