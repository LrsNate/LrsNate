package controllers

import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.json.Json
import play.api.libs.ws.WS
import play.api.mvc.{Action, Controller}
import play.api.Play.current

object Anime extends Controller {

  def watching = Action.async {
    val req = WS.url("https://hummingbirdv1.p.mashape.com/users/LrsNate/library")
      .withRequestTimeout(5000)
      .withQueryString("status" -> "currently-watching")
      .withHeaders("X-Mashape-Key" -> "jI7Tfte3Inmsh3o1j5UkrJvdsl9dp1K5axvjsnA0x1tViNPNrM")
      .get()

    req map { res =>
       Ok(res.json)
    } recover {
      case e: Throwable => InternalServerError(Json.obj("error" -> e.getMessage))
    }
  }
}
