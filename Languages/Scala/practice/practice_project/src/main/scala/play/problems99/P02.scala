package play.problems99

object Main02 extends App {

  println(P02.penultimate(List(1, 1, 2, 3, 5, 8))) // 5
  println(P02.penultimateRec(List(1, 1, 2, 3, 5, 8))) // 5
  println(P02.penultimateRecOption(List(1, 1, 2, 3, 5, 8))) // Some(5)
  println()
  println(P02.lastNth(1, List(1, 1, 2, 3, 5, 8))) // 8
  println(P02.lastNth(2, List(1, 1, 2, 3, 5, 8))) // 5
  println(P02.lastNth(3, List(1, 1, 2, 3, 5, 8))) // 3
  println(P02.lastNth(4, List(1, 1, 2, 3, 5, 8))) // 2
  println(P02.lastNth(5, List(1, 1, 2, 3, 5, 8))) // 1
  println(P02.lastNth(6, List(1, 1, 2, 3, 5, 8))) // 1
  println()
  println(P02.lastNthOption(1, List(1, 1, 2, 3, 5, 8))) // Some(8)
  println(P02.lastNthOption(2, List(1, 1, 2, 3, 5, 8))) // Some(5)
  println(P02.lastNthOption(3, List(1, 1, 2, 3, 5, 8))) // Some(3)
  println(P02.lastNthOption(4, List(1, 1, 2, 3, 5, 8))) // Some(2)
  println(P02.lastNthOption(5, List(1, 1, 2, 3, 5, 8))) // Some(1)
  println(P02.lastNthOption(6, List(1, 1, 2, 3, 5, 8))) // Some(1)
  println()
  println(P02.lastNthRec(1, List(1, 1, 2, 3, 5, 8))) // 8
  println(P02.lastNthRec(2, List(1, 1, 2, 3, 5, 8))) // 5
  println(P02.lastNthRec(3, List(1, 1, 2, 3, 5, 8))) // 3
  println(P02.lastNthRec(4, List(1, 1, 2, 3, 5, 8))) // 2
  println(P02.lastNthRec(5, List(1, 1, 2, 3, 5, 8))) // 1
  println(P02.lastNthRec(6, List(1, 1, 2, 3, 5, 8))) // 1
  println()
  println(P02.lastNthRecOption(1, List(1, 1, 2, 3, 5, 8))) // Some(8)
  println(P02.lastNthRecOption(2, List(1, 1, 2, 3, 5, 8))) // Some(5)
  println(P02.lastNthRecOption(3, List(1, 1, 2, 3, 5, 8))) // Some(3)
  println(P02.lastNthRecOption(4, List(1, 1, 2, 3, 5, 8))) // Some(2)
  println(P02.lastNthRecOption(5, List(1, 1, 2, 3, 5, 8))) // Some(1)
  println(P02.lastNthRecOption(6, List(1, 1, 2, 3, 5, 8))) // Some(1)
  println()
  println(P02.lastNthRecEasy(1, List(1, 1, 2, 3, 5, 8))) // 8
  println(P02.lastNthRecEasy(2, List(1, 1, 2, 3, 5, 8))) // 5
  println(P02.lastNthRecEasy(3, List(1, 1, 2, 3, 5, 8))) // 3
  println(P02.lastNthRecEasy(4, List(1, 1, 2, 3, 5, 8))) // 2
  println(P02.lastNthRecEasy(5, List(1, 1, 2, 3, 5, 8))) // 1
  println(P02.lastNthRecEasy(6, List(1, 1, 2, 3, 5, 8))) // 1
  println()
  println(P02.lastNthRecEasyOption(1, List(1, 1, 2, 3, 5, 8))) // Some(8)
  println(P02.lastNthRecEasyOption(2, List(1, 1, 2, 3, 5, 8))) // Some(5)
  println(P02.lastNthRecEasyOption(3, List(1, 1, 2, 3, 5, 8))) // Some(3)
  println(P02.lastNthRecEasyOption(4, List(1, 1, 2, 3, 5, 8))) // Some(2)
  println(P02.lastNthRecEasyOption(5, List(1, 1, 2, 3, 5, 8))) // Some(1)
  println(P02.lastNthRecEasyOption(6, List(1, 1, 2, 3, 5, 8))) // Some(1)

}

/**
  * Find the last but one element of a list.
  * Additional: Find the last n-th element of a list.
  */
object P02 {

  def penultimate[T](ls: List[T]) = {
    if (ls.size < 2) throw new NoSuchElementException
    else ls.init.last
  }

  def penultimateRec[T](ls: List[T]): T = ls match {
    case h :: _ :: Nil => h
    case _ :: tail => penultimateRec(tail)
    case _ => throw new NoSuchElementException
  }

  def penultimateRecOption[T](ls: List[T]): Option[T] = ls match {
    case h :: _ :: Nil => Some(h)
    case _ :: tail => penultimateRecOption(tail)
    case _ => None
  }

  def lastNth[T](n: Int, ls: List[T]) = {
    if (n <= 0) throw new IllegalArgumentException
    if (n > ls.size) throw new NoSuchElementException
    ls.takeRight(n).head
  }

  def lastNthOption[T](n: Int, ls: List[T]) = {
    if (n <= 0 || n > ls.size) None else Some(ls.takeRight(n).head)
  }

  def lastNthRec[T](n: Int, ls: List[T]): T = {
    if (n <= 0) throw new IllegalArgumentException
    if (n > ls.size) throw new NoSuchElementException
    ls match {
      case h :: Nil => h
      case h :: _ if n == ls.size => h
      case _ :: tail if tail.size == n => tail.head
      case _ :: tail => lastNthRec(n, tail)
      case _ => throw new NoSuchElementException
    }
  }

  def lastNthRecOption[T](n: Int, ls: List[T]): Option[T] = {
    if (n <= 0 || n > ls.size) None
    ls match {
      case h :: Nil => Some(h)
      case h :: _ if n == ls.size => Some(h)
      case _ :: tail if tail.size == n => Some(tail.head)
      case _ :: tail => lastNthRecOption(n, tail)
      case _ => None
    }
  }

  // easiest rec solution
  def lastNthRecEasy[T](n: Int, ls: List[T]): T = {
    if (n <= 0) throw new IllegalArgumentException
    if (n > ls.size) throw new NoSuchElementException
    if (n == ls.size) ls.head else lastNthRecEasy(n, ls.tail)
  }

  // easiest rec solution (1 liner)
  def lastNthRecEasyOption[T](n: Int, ls: List[T]): Option[T] = {
    if (n <= 0 || n > ls.size) None else if (n == ls.size) Some(ls.head) else lastNthRecEasyOption(n, ls.tail)
  }

}

