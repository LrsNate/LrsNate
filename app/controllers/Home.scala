package controllers

import play.api.mvc.{Action, Controller}

object Home extends Controller {
  def index = Action(Redirect(routes.About.me()))
}