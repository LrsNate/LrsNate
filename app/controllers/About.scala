package controllers

import play.api.mvc.{Action, Controller}

object About extends Controller {

  def me = Action(Ok(views.html.aboutMe()))

  def site = Action(Ok(views.html.aboutSite()))
}
