package controllers

import play.api.mvc.Action

/**
 * Created by Nate on 18/10/15.
 */
object AdvanceWars extends RESTController {
  def index = Action(Ok(views.html.aw_index()))
}
