package controllers

import javax.inject.{Inject, Singleton}

import akka.actor.ActorSystem
import controllers.Application.Ok
import play.api.db.DB
import play.api.mvc._
import play.api.Play.current

import scala.concurrent.ExecutionContext

@Singleton
class FakeController extends Controller {
  def fake = Action {
    var out = ""
    val conn = DB.getConnection()
    try {
      val stmt = conn.createStatement
      val rs = stmt.executeQuery("SELECT tick FROM ticks order by tick desc limit 1")
      rs.next()


        out += "Last tick " + rs.getTimestamp("tick") + "\n"

    } finally {
      conn.close()
    }
    Ok(out)
  }

}
