package controllers

import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.ws.WS
import play.api.mvc.Action
import play.api.Play.current
import play.api.Play.configuration

object Tumblr extends RESTController {

  val apiKey = configuration.getString("tumblr.apiKey").get
  val blogName = configuration.getString("tumblr.blogName").get

  def posts = cached("tumblr.posts")(Action.async {
    val req = WS
      .url(s"http://api.tumblr.com/v2/blog/$blogName/posts")
      .withQueryString(
        "api_key" -> apiKey,
        "limit" -> "5"
      )
      .get()

    req map { res =>
      Ok(res.json)
    }
  })
}
