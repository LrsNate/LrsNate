package mocks

import dao.GameDao
import models.{Mob, Cell, Grid, Game}

import scala.concurrent.Future

/**
 * Created by Nate on 02/11/15.
 */
class GameDaoMock extends GameDao {
  override def getGameState(gameId: String): Future[Option[Game]] = gameId match {
    case "1" => Future.successful(Some(
      Game("1", Grid(Seq(Seq(Cell("plain"), Cell("plain"))), Seq(Mob("inftr", 0, 0))))
    ))
    case _ => Future.successful(None)
  }
}
