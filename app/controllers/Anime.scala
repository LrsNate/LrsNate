package controllers

import javax.inject.Inject

import play.api.Configuration
import play.api.cache.Cached
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.json.{JsArray, Json}
import play.api.libs.ws.WSClient
import play.api.mvc.{Action, Controller}
import utils.AnimeListUtil


class Anime @Inject()(cached: Cached, configuration: Configuration, wsClient: WSClient) extends Controller {

  val animeListUtil = new AnimeListUtil(configuration, wsClient)

  implicit val username = configuration.getString("hummingbird.username").get
  val apiKey = configuration.getString("hummingbird.apiKey").get

  def lists = cached("anime.lists")(Action.async {
    for {
      watching <- animeListUtil.getList("currently-watching") flatMap {
        watching =>
          animeListUtil.getLast(watching.json.as[JsArray], None)
      }
      completed <- animeListUtil.getList("completed") flatMap {
        completed =>
          animeListUtil.getLast(completed.json.as[JsArray], Some(5))
      }
    } yield Ok(Json.obj(
      "watching" -> watching,
      "completed" -> completed
    ))
  })
}
