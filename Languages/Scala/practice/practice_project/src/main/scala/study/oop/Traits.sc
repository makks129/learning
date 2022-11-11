// https://www.scala-exercises.org/std_lib/traits

/**
  * Unlike Java, Scala allows traits to be partially implemented; i.e. it is possible to define default implementations for some methods
  * Traits may not have constructor parameters
  */

trait Similarity {
  def isSimilar(x: Any): Boolean

  // no concrete implementation (like abstract in Java)
  def isNotSimilar(x: Any): Boolean = !isSimilar(x) // has concrete implementation (like default in Java 8)
}

//

case class Event(name: String)

trait EventListener {
  def listen(event: Event): String
}

class MyListener extends EventListener {
  def listen(event: Event): String = event match {
    case Event("Moose Stampede") => "An unfortunate moose stampede occurred"
    case _ => "Nothing of importance occurred"
  }
}

val event = Event("Moose Stampede")
val myListener = new MyListener
myListener.listen(event)


/** Mixins **/

// See http://www.scala-lang.org/old/node/117