package controllers

import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.json.{JsArray, Json}
import play.api.libs.ws.{WSResponse, WS}
import play.api.mvc.Action
import play.api.Play.current
import play.api.Play.configuration
import utils.AnimeList

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
      getList("completed") flatMap { completed =>
        AnimeList.getLast(watching.json.as[JsArray], None) flatMap { w =>
          AnimeList.getLast(completed.json.as[JsArray], Some(5)) map { c =>
            Ok(Json.obj(
              "watching" -> w,
              "completed" -> c
            ))
          }
        }
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
