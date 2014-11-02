package controllers

import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.json.JsArray
import play.api.libs.oauth.{OAuthCalculator, ConsumerKey, RequestToken}
import play.api.libs.ws.WS
import play.api.mvc.Action
import play.api.Play.current

object Twitter extends RESTController {

  val twitter = "https://api.twitter.com/1.1"

  val oauth = OAuthCalculator(
    ConsumerKey(
      "BsCQCcC4UK3IjtNJFH4q6ooIV",
      "TzQOOlbzZjYE20HgFBwmHs2mVw3oy0wsI6uwyBrKc2W8ZhwZrc"
    ),
    RequestToken(
      "238788878-rX76fZFi5chEAyUGtfwSL8EorWfF4ZU83Fx1cXoA",
      "CWxOblltVJUVK5KtHrpXxWgaa1c2Vk0MFaLuEF7TFvLkF"
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
