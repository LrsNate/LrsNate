package dao

import models.{Move, Game}
import play.api.Play.current
import play.api.libs.json.{JsObject, Json}
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.modules.reactivemongo.ReactiveMongoPlugin
import play.modules.reactivemongo.json._
import play.modules.reactivemongo.json.collection._

import scala.concurrent.Future
import scala.util.{Failure, Success, Try}

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

  override def moveUnit(move: Move): Future[Try[Unit]] = {
    getGameState(move.gameId) flatMap {
      case None => Future.successful(Failure(new NoSuchElementException("No game found in database with this id.")))
      case Some(g) =>
        val newGrid = Try(g.grid moveUnit move)
        newGrid match {
          case Success(ng) =>
            val writeResult = games.update(
              Json.obj("game_id" -> g.gameId),
              Json.toJson(Game(g.gameId, ng)).as[JsObject])
            writeResult map { res =>
              if (res.ok) Success((): Unit)
              else Failure(res)
            }
          case Failure(e) => Future.successful(Failure(e))
        }

    }
  }
}
