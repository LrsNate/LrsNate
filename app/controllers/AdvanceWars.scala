package controllers

import dao.{MongoGameDao, GameDao}
import models.Move
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.json.{JsSuccess, JsError, JsValue, Json}
import play.api.mvc.{Request, Controller, Action}

import scala.concurrent.Future
import scala.util.{Success, Failure}

/**
 * Created by Nate on 18/10/15.
 */
class AdvanceWars(gameDao: GameDao) extends Controller {

  def index = Action(Ok(views.html.aw_index()))

  def getGameState(gameId: String) = Action.async {
    gameDao.getGameState(gameId) map { opt =>
      opt map { game =>
        Ok(Json.toJson(game))
      } getOrElse NotFound
    }
  }

  def moveUnit = Action.async(parse.json) { req =>
    req.body.validate[Move] match {
      case e: JsError =>
        Future.successful(BadRequest(JsError.toFlatJson(e.errors)))
      case s: JsSuccess[Move] =>
        gameDao.moveUnit(s.get) map {
          case Failure(e: NoSuchElementException) =>
            NotFound(Json.obj("error" -> e.getMessage))
          case Failure(e) =>
            Forbidden(Json.obj("error" -> e.getMessage))
          case _: Success[Unit] =>
            Ok(Json.obj("acknowledged" -> true))
        }
    }
  }
}

object AdvanceWars extends AdvanceWars(new MongoGameDao)