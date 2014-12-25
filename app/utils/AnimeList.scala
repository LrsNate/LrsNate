package utils

import play.api.Play._
import play.api.libs.json.{Json, JsObject, JsArray}
import play.api.libs.ws.WS
import play.api.libs.concurrent.Execution.Implicits.defaultContext

import scala.concurrent.Future

object AnimeList {

  val apiKey = configuration.getString("hummingbird.apiKey").get

  def getLast(list: JsArray, n: Option[Int]): Future[JsArray] = {
    val base = list.as[Seq[JsObject]].sortBy(o => (o \ "updated_at").as[String]).reverse
    val single = n map { base.take } getOrElse base
    joinGenres(single) map JsArray.apply
  }

  def joinGenres(list: Seq[JsObject]): Future[Seq[JsObject]] = {
    val res = list.map { o =>
      val id = (o \ "anime" \ "id").as[Int]
      WS.url(s"https://hummingbirdv1.p.mashape.com/anime/$id")
        .withRequestTimeout(5000)
        .withHeaders("X-Mashape-Key" -> apiKey)
        .get() map { a =>
        val anime = o \ "anime"
        val genres = a.json \ "genres"
        o ++ Json.obj("anime" -> (anime.as[JsObject] ++ Json.obj("genres" -> genres)))
      }
    }
    Future.sequence(res)
  }

}
