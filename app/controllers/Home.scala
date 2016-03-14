package controllers

import javax.inject.Inject

import play.api.cache.Cached
import play.api.mvc.{Action, Controller}

class Home @Inject()(cached: Cached, webJarAssets: WebJarAssets) extends Controller {
  def index = cached("home")(Action(Ok(views.html.index(webJarAssets))))
}