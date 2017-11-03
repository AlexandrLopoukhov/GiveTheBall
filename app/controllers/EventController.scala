package controllers

import javax.inject.Singleton

import managers.EventManager
import model.Event
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}

@Singleton
class EventController  extends Controller {

  def events = Action {
    val events: List[Event] = EventManager.getEvents()
    Ok(Json.toJson(events))
  }

}
