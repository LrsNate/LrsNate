package controllers

import javax.inject.Inject

import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.json.{JsObject, Json}
import play.api.mvc.{Action, Controller}
import play.modules.reactivemongo.{MongoController, ReactiveMongoApi, ReactiveMongoComponents}
import reactivemongo.play.json._
import reactivemongo.play.json.collection.JSONCollection

class League @Inject()(val reactiveMongoApi: ReactiveMongoApi) extends Controller with MongoController with ReactiveMongoComponents {

  val games = db.collection[JSONCollection]("games")

  def get = Action.async {
    val cursor = games.find(Json.obj()).one[JsObject]
    cursor map {
      js => Ok(js.get)
    }
  }
}
