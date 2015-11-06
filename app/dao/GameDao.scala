package dao

import models.{Move, Game}

import scala.concurrent.Future
import scala.util.Try

/**
 * Created by Nate on 02/11/15.
 */
trait GameDao {
  def getGameState(gameId: String): Future[Option[Game]]

  def moveUnit(move: Move): Future[Try[Unit]]
}
