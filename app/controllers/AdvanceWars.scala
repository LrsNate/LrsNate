package controllers

import dao.{MongoGameDao, GameDao}
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.json.Json
import play.api.mvc.{Controller, Action}

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

  def moveUnit = Action(parse.json) { body =>
    NotImplemented
  }
}

object AdvanceWars extends AdvanceWars(new MongoGameDao)