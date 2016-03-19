package controllers

import javax.inject.Inject

import play.api.Configuration
import play.api.cache.Cached
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.json.JsArray
import play.api.libs.oauth.{ConsumerKey, OAuthCalculator, RequestToken}
import play.api.libs.ws.WSClient
import play.api.mvc.{Action, Controller}

class Twitter @Inject()(cached: Cached, configuration: Configuration, wsClient: WSClient) extends Controller {

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
    val req = wsClient.url(twitter + "/statuses/mentions_timeline.json")
      .withQueryString("count" -> "1")
      .sign(oauth)
      .get()

    req flatMap { e =>
      val mentions = e.json.as[JsArray]
      val emb = wsClient.url(twitter + "/statuses/oembed.json")
        .withQueryString(
          "id" -> mentions(0).\("id").get.toString()
        ).sign(oauth)
        .get()
      emb map {
        e => Ok(e.json)
      }
    }
  })
}
