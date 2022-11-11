package progfun2.week3.discrete_event_simulation

abstract class Simulation {

  type Action = () => Unit

  case class Event(time: Int, action: Action)

  private type Agenda = List[Event]
  private var agenda: Agenda = List()

  private var curtime = 0

  def currentTime: Int = curtime

  def afterDelay(delay: Int)(block: => Unit): Unit = {
    val item = Event(curtime + delay, () => block)
    agenda = insert(agenda, item)
  }

  private def insert(ag: Agenda, item: Event): Agenda = ag match {
    case h :: t if h.time <= item.time => h :: insert(t, item)
    case _ => item :: ag
  }

  private def loop(): Unit = agenda match {
    case h :: t =>
      agenda = t
      curtime = h.time
      h.action()
      loop()
    case Nil =>
  }

  def run(): Unit = {
    afterDelay(0) {
      println("*** simulation started, time = " + currentTime + " ***")
    }
    loop()
  }

}
