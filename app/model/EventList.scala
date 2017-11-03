package model

import play.api.libs.json.{JsValue, Json, Writes}

class EventList(list: List[Event]) {
  val output: List[Event] = list
}

object EventList {
  implicit val implicitBarWrites = new Writes[EventList] {
    def writes(eventList: EventList): JsValue = {
      Json.obj(
        "events" -> eventList.output
      )
    }
  }
}
