package model

import play.api.libs.json.{JsValue, Json, Writes}

case class EventList(list: List[Event])

object EventList {
  implicit val implicitBarWrites = new Writes[EventList] {
    def writes(eventList: EventList): JsValue = {
      Json.obj(
        "events" -> eventList.list
      )
    }
  }
}

