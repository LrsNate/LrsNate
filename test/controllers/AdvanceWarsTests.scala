package controllers

import models.Grid
import org.scalatest.{Matchers, FlatSpec}
import play.api.mvc.Controller
import play.api.test.{Helpers, FakeRequest}
import play.api.test.Helpers.defaultAwaitTimeout

/**
 * Created by Nate on 30/10/15.
 */
class AdvanceWarsTests extends FlatSpec with Matchers {

  class AdvanceWarsTestCtrl extends Controller with AdvanceWars

  val controller = new AdvanceWarsTestCtrl

  "The default map controller" should "return a valid grid" in {
    val result = controller.defaultMap.apply(FakeRequest())
    val body = Helpers.contentAsJson(result)
    body.as[Grid]
  }
}
