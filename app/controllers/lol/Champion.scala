package controllers.lol

import javax.inject.Inject

import play.api.cache.Cached
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.json._
import play.api.libs.ws.WSClient
import play.api.mvc.{Action, Controller}
import play.api.{Configuration, Logger}
import play.modules.reactivemongo.{MongoController, ReactiveMongoApi, ReactiveMongoComponents}
import reactivemongo.api.indexes.{Index, IndexType}
import reactivemongo.play.json._
import reactivemongo.play.json.collection._

import scala.concurrent.Future

class Champion @Inject()(wsClient: WSClient, configuration: Configuration, cached: Cached, val reactiveMongoApi: ReactiveMongoApi) extends Controller with MongoController with ReactiveMongoComponents {

  val champions = db.collection[JSONCollection]("champions")

  champions.indexesManager.ensure(new Index(Seq("id" -> IndexType.Ascending), None, true))

  def imageUrl(name: String) = s"http://ddragon.leagueoflegends.com/cdn/6.5.1/img/champion/$name.png"

  val LOL_API_KEY = configuration.getString("lol.apiKey").get

  val CHAMPIONS_URL = s"https://global.api.pvp.net/api/lol/static-data/euw/v1.2/champion?locale=en_US&api_key=$LOL_API_KEY"

  def getImage(name: String) = {
    def getImageAction = Action.async {
      wsClient.url(imageUrl(name)).get() map { r =>
        Ok(r.bodyAsBytes).withHeaders("Content-Type" -> "image/png")
      }
    }
    cached(s"championImage_$name")(getImageAction)
  }

  def refresh = Action.async {
    wsClient.url(CHAMPIONS_URL).get() map { response =>
      println(CHAMPIONS_URL)
      println(response.json)
      (response.json \ "data").get.as[JsObject].value foreach { kv =>
        println(kv._2.toString())
        champions.update(Json.obj("id" -> (kv._2 \ "name").get), kv._2.as[JsObject], upsert = true)
      }
      Ok
    }
  }

}
