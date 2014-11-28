package controllers

import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.json.JsArray
import play.api.libs.oauth.{OAuthCalculator, ConsumerKey, RequestToken}
import play.api.libs.ws.WS
import play.api.mvc.Action
import play.api.Play.current
import play.api.Play.configuration

object Twitter extends RESTController {

  val twitter = "https://api.twitter.com/1.1"

  val appKey = configuration.getString("twitter.app.key").get
  val appSecret = configuration.getString("twitter.app.secret").get

  val accountToken = configuration.getString("twitter.account.token").get
  val accountSecret = configuration.getString("twitter.account.secret").get

  val oauth = OAuthCalculator(
    ConsumerKey(
      appKey,
      appSecret
    ),
    RequestToken(
      accountToken,
      accountSecret
    )
  )

  def lastMention = cached("twitter.lastMention")(Action.async {
    val req = WS.url(twitter + "/statuses/mentions_timeline.json")
      .withQueryString("count" -> "1")
      .sign(oauth)
      .get()

    req flatMap { e =>
      val emb = WS.url(twitter + "/statuses/oembed.json")
        .withQueryString(
          "id" -> e.json.asInstanceOf[JsArray](0).\("id").toString,
          "omit_script" -> "true"
        ).sign(oauth)
        .get()
      emb map {
        e => Ok(e.json)
      }
    }
  })
}
