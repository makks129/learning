package play.problems99

object Main03 extends App {

  println(P03.nth(0, List(1, 1, 2, 3, 5, 8))) // 1
  println(P03.nth(1, List(1, 1, 2, 3, 5, 8))) // 1
  println(P03.nth(2, List(1, 1, 2, 3, 5, 8))) // 2
  println(P03.nth(3, List(1, 1, 2, 3, 5, 8))) // 3
  println(P03.nth(4, List(1, 1, 2, 3, 5, 8))) // 5
  println(P03.nth(5, List(1, 1, 2, 3, 5, 8))) // 8
  println()
  println(P03.nthRec(0, List(1, 1, 2, 3, 5, 8))) // 1
  println(P03.nthRec(1, List(1, 1, 2, 3, 5, 8))) // 1
  println(P03.nthRec(2, List(1, 1, 2, 3, 5, 8))) // 2
  println(P03.nthRec(3, List(1, 1, 2, 3, 5, 8))) // 3
  println(P03.nthRec(4, List(1, 1, 2, 3, 5, 8))) // 5
  println(P03.nthRec(5, List(1, 1, 2, 3, 5, 8))) // 8
  println()
  println(P03.nthRecOption(0, List(1, 1, 2, 3, 5, 8))) // Some(1)
  println(P03.nthRecOption(1, List(1, 1, 2, 3, 5, 8))) // Some(1)
  println(P03.nthRecOption(2, List(1, 1, 2, 3, 5, 8))) // Some(2)
  println(P03.nthRecOption(3, List(1, 1, 2, 3, 5, 8))) // Some(3)
  println(P03.nthRecOption(4, List(1, 1, 2, 3, 5, 8))) // Some(5)
  println(P03.nthRecOption(5, List(1, 1, 2, 3, 5, 8))) // Some(8)
  println()
  println(P03.nthRec2(0, List(1, 1, 2, 3, 5, 8))) // 1
  println(P03.nthRec2(1, List(1, 1, 2, 3, 5, 8))) // 1
  println(P03.nthRec2(2, List(1, 1, 2, 3, 5, 8))) // 2
  println(P03.nthRec2(3, List(1, 1, 2, 3, 5, 8))) // 3
  println(P03.nthRec2(4, List(1, 1, 2, 3, 5, 8))) // 5
  println(P03.nthRec2(5, List(1, 1, 2, 3, 5, 8))) // 8
  println()
  println(P03.nthRec2Option(0, List(1, 1, 2, 3, 5, 8))) // Some(1)
  println(P03.nthRec2Option(1, List(1, 1, 2, 3, 5, 8))) // Some(1)
  println(P03.nthRec2Option(2, List(1, 1, 2, 3, 5, 8))) // Some(2)
  println(P03.nthRec2Option(3, List(1, 1, 2, 3, 5, 8))) // Some(3)
  println(P03.nthRec2Option(4, List(1, 1, 2, 3, 5, 8))) // Some(5)
  println(P03.nthRec2Option(5, List(1, 1, 2, 3, 5, 8))) // Some(8)

}

/**
  * Find the Kth element of a list.
  */
object P03 {

  def nth[T](n: Int, ls: List[T]) =
    if (n >= 0) ls(n) else throw new IllegalArgumentException

  def nthRec[T](n: Int, ls: List[T]): T = {
    if (n == 0) ls.head else nthRec(n-1, ls.tail)
  }

  def nthRecOption[T](n: Int, ls: List[T]): Option[T] = {
    if (n == 0) Some(ls.head) else nthRecOption(n-1, ls.tail)
  }

  def nthRec2[T](n: Int, ls: List[T]): T = (n, ls) match {
    case (0, h :: _) => h
    case (currN, _ :: tail) => nthRec2(currN-1, tail)
    case _ => throw new NoSuchElementException
  }

  def nthRec2Option[T](n: Int, ls: List[T]): Option[T] = (n, ls) match {
    case (0, h :: _) => Some(h)
    case (currN, _ :: tail) => nthRec2Option(currN-1, tail)
    case _ => None
  }

}

