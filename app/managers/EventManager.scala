package managers

import dao.EventDAO
import model.Event

object EventManager {
  def getEvents(): List[Event] = {
    EventDAO.getEvents()

  }

}
