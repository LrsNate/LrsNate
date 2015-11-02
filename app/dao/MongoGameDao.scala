package dao

import models.Game
import play.api.Play.current
import play.api.libs.json.{JsObject, Json}
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.modules.reactivemongo.ReactiveMongoPlugin
import play.modules.reactivemongo.json._
import play.modules.reactivemongo.json.collection.JSONCollection

import scala.concurrent.Future

/**
 * Created by Nate on 02/11/15.
 */
class MongoGameDao extends GameDao {
  val db = ReactiveMongoPlugin.db
  val games = db.collection[JSONCollection]("games")

  override def getGameState(gameId: String): Future[Option[Game]] = {
    val jsobj = games.find(Json.obj("game_id" -> gameId)).one[JsObject]

    jsobj map { opt =>
      opt map { obj =>
        obj.as[Game]
      }
    }
  }
}
