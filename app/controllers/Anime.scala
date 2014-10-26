package controllers

import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.json.Json
import play.api.libs.ws.{WSResponse, WS}
import play.api.mvc.Action
import play.api.Play.current

import scala.concurrent.Future

object Anime extends RESTController {

  def getList(status: String): Future[WSResponse] =
    WS.url("https://hummingbirdv1.p.mashape.com/users/LrsNate/library")
      .withRequestTimeout(5000)
      .withQueryString("status" -> status)
      .withHeaders("X-Mashape-Key" -> "jI7Tfte3Inmsh3o1j5UkrJvdsl9dp1K5axvjsnA0x1tViNPNrM")
      .get()

  def lists = cached("anime.lists")(Action.async {
    getList("currently-watching") flatMap { watching =>
      getList("completed") map { completed =>
        Ok(Json.obj(
          "watching" -> watching.json,
          "completed" -> completed.json
        ))
      } recover {
        case e: Throwable => InternalServerError(Json.obj("error" -> e.getMessage))
      }
    } recover {
      case e: Throwable => InternalServerError(Json.obj("error" -> e.getMessage))
    }
  })

  def watching = cached("anime.watching")(Action.async {
    getList("currently-watching") map { res =>
       Ok(res.json)
    } recover {
      case e: Throwable => InternalServerError(Json.obj("error" -> e.getMessage))
    }
  })
}
