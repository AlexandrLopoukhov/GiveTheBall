package model

import play.api.libs.json.{JsValue, Json, Writes}

case class Event(id: Long,
                 title: String,
                 `type`: String,
                 date: String,
                 allPeopleNumber: Integer,
                 bookedPeopleNumber: Integer,
                 address: String,
                 description: String)

object Event {
  implicit val implicitBarWrites = new Writes[Event] {
    def writes(event: Event): JsValue = {
      Json.obj(
        "id" -> event.id,
        "title" -> event.title,
        "type" -> event.`type`,
        "date" -> event.date,
        "allPeopleNumber" -> event.allPeopleNumber.toString,
        "bookedPeopleNumber" -> event.bookedPeopleNumber.toString,
        "address" -> event.address,
        "description" -> event.description
      )
    }
  }
}
