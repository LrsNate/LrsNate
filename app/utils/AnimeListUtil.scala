package utils

import play.api.Configuration
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.json.{JsArray, JsObject, Json}
import play.api.libs.ws.{WSClient, WSResponse}

import scala.concurrent.Future


class AnimeListUtil(configuration: Configuration, ws: WSClient) {

  val apiKey = configuration.getString("hummingbird.apiKey").get

  def getList(status: String)(implicit username: String): Future[WSResponse] =
    ws.url(s"https://hummingbirdv1.p.mashape.com/users/$username/library")
      .withRequestTimeout(5000)
      .withQueryString("status" -> status)
      .withHeaders("X-Mashape-Key" -> apiKey)
      .get()

  def getLast(list: JsArray, n: Option[Int]): Future[JsArray] = {
    val base = list.as[Seq[JsObject]].sortBy(o => (o \ "updated_at").as[String]).reverse
    val single = n map {
      base.take
    } getOrElse base
    joinGenres(single) map JsArray.apply
  }

  def joinGenres(list: Seq[JsObject]): Future[Seq[JsObject]] = {
    val res = list.map { o =>
      val id = (o \ "anime" \ "id").as[Int]
      ws.url(s"https://hummingbirdv1.p.mashape.com/anime/$id")
        .withRequestTimeout(5000)
        .withHeaders("X-Mashape-Key" -> apiKey)
        .get() map { a =>
        val anime = (o \ "anime").get
        val genres = (a.json \ "genres").get
        o ++ Json.obj("anime" -> (anime.as[JsObject] ++ Json.obj("genres" -> genres)))
      }
    }
    Future.sequence(res)
  }

}
