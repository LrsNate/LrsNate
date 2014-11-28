package controllers

import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.json.Json
import play.api.libs.ws.{WSResponse, WS}
import play.api.mvc.Action
import play.api.Play.current
import play.api.Play.configuration

import scala.concurrent.Future

object Anime extends RESTController {

  val username = configuration.getString("hummingbird.username").get
  val apiKey = configuration.getString("hummingbird.apiKey").get

  def getList(status: String): Future[WSResponse] =
    WS.url(s"https://hummingbirdv1.p.mashape.com/users/$username/library")
      .withRequestTimeout(5000)
      .withQueryString("status" -> status)
      .withHeaders("X-Mashape-Key" -> apiKey)
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
