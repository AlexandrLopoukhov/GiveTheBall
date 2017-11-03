package controllers

import javax.inject.Singleton

import managers.EventManager
import model.{Event, EventList}
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}

@Singleton
class EventController  extends Controller {

  def events = Action {
    val events: EventList =new EventList(EventManager.getEvents())
    Ok(Json.toJson(events))
  }
}
