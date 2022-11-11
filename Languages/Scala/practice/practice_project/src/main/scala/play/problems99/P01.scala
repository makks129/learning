package play.problems99

object Main01 extends App {

  println(P01.last(List(1, 1, 2, 3, 5, 8))) // 8
  println(P01.lastRec(List(1, 1, 2, 3, 5, 8))) // 8
  println(P01.lastRecOption(List(1, 1, 2, 3, 5, 8))) // Some(8)

}

/**
  * Find the last element of a list.
  */
object P01 {

  def last[T](ls: List[T]) = ls.last

  def lastRec[T](ls: List[T]): T = ls match {
    case h :: Nil => h
    case _ :: tail => lastRec(tail)
    case _ => throw new NoSuchElementException
  }

  def lastRecOption[T](ls: List[T]): Option[T] = ls match {
    case h :: Nil => Some(h)
    case _ :: tail => lastRecOption(tail)
    case _ => None
  }

}

