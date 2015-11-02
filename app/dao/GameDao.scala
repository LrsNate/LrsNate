package dao

import models.Game

import scala.concurrent.Future

/**
 * Created by Nate on 02/11/15.
 */
trait GameDao {
  def getGameState(gameId: String): Future[Option[Game]]
}
