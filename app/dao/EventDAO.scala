package dao

import java.sql.Timestamp

import model.Event
import play.api.db.DB
import play.api.Play.current

import scala.collection.mutable

object EventDAO {
  def getEvents(): mutable.MutableList[Event] = {
    var out: mutable.MutableList[Event] = mutable.MutableList[Event]()

    val conn = DB.getConnection()
    try {
      val stmt = conn.createStatement
      val rs = stmt.executeQuery("""SELECT e.id
                                         , e.name
                                         , t.name as type
                                         , p.photo
                                         , e.min_num_particip
                                         , e.max_num_particip
                                         , e.description
                                         , d.date
                                         , e.demands
                                    FROM   event as e
                                    LEFT
                                    JOIN   type as t
                                    ON     e.type_id = t.id
                                    LEFT
                                    JOIN   date as d
                                    on     e.date_id = d.id
                                    LEFT
                                    JOIN   picture as p
                                    ON     e.picture_id = p.id""")

      while (
        rs.next()
      ) {
        val id: Long = rs.getLong("id")
        val title: String = rs.getString("name")
        val description: String = rs.getString("description")
        val date: Timestamp = rs.getTimestamp("date")
        val allPeopleNumber: Int = rs.getInt("min_num_particip")
        val bookedPeopleNumber: Int = rs.getInt("max_num_particip")
        val `type`: String = rs.getString("type")
        val adress: String = rs.getString("demands")
        val event: Event = new Event(id, title, `type`, date.toLocalDateTime.toString, allPeopleNumber, bookedPeopleNumber, adress, description)
        out.+=(event)

      }
      out


    } finally {

      conn.close()
    }
  }

}
