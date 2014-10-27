package controllers

import play.api.cache.Cached
import play.api.mvc.Controller
import play.api.Play.current

trait RESTController extends Controller {

  val cacheDuration = 3600 * 24

  def cached(key: String) = Cached.status(_ => key, OK, cacheDuration)(_)
}
