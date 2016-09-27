package controllers

import java.io.File

import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}

/**
  * Created by Nate on 27/09/16.
  */
class Martial extends Controller {
  def databaseLastModified = Action {
    Ok(Json.obj("lastModified" -> new File("public/resources/works.db").lastModified))
  }
}
