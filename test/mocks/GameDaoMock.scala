package mocks

import dao.GameDao
import models._

import scala.concurrent.Future
import scala.util.{Failure, Success, Try}

/**
 * Created by Nate on 02/11/15.
 */
class GameDaoMock extends GameDao {

  val game = Game("1", Grid(Seq(Seq(Cell("plain"), Cell("plain"), Cell("plain"))),
    Seq(Mob("inftr", 0, 0), Mob("inftr", 0, 2))))
  override def getGameState(gameId: String): Future[Option[Game]] = gameId match {
    case "1" => Future.successful(Some(game))
    case _ => Future.successful(None)
  }

  override def moveUnit(move: Move): Future[Try[Unit]] = {
    move.gameId match {
      case "1" =>
        Future.successful(Try {
          game.grid moveUnit move
          ()
        })
      case _ => Future.successful(Failure(new NoSuchElementException("Unknown game id")))
    }
  }
}
