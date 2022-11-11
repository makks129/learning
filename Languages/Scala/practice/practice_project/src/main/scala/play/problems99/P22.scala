package play.problems99

import scala.annotation.tailrec

object Main22 extends App {

  println(P22.range(4, 9)) // List(4, 5, 6, 7, 8, 9)
  println(P22.range_2(4, 9)) // List(4, 5, 6, 7, 8, 9)
  println(P22.range_R(4, 9)) // List(4, 5, 6, 7, 8, 9)
  println(P22.range_TR(4, 9)) // List(4, 5, 6, 7, 8, 9)
  println(P22.range_L(4, 9)) // List(4, 5, 6, 7, 8, 9)
  println(P22.range_FP(4, 9)) // List(4, 5, 6, 7, 8, 9)

}

/**
  * Create a list containing all integers within a given range.
  */
object P22 {

  def range(n1: Int, n2: Int) = (n1 to n2).toList

  def range_2(n1: Int, n2: Int) = List.range(n1, n2 + 1)

  def range_R(n1: Int, n2: Int): List[Int] =
    if (n1 > n2) Nil else n1 :: range_R(n1 + 1, n2)

  def range_TR(f: Int, t: Int) = {
    @tailrec def range_TR_acc(f2: Int, res: List[Int]): List[Int] =
      if (f2 > t) res else range_TR_acc(f2 + 1, res :+ f2)
    range_TR_acc(f, Nil)
  }

  def range_L(f: Int, t: Int) = (for (i <- f to t) yield i).toList

  // Not mine. From: http://aperiodic.net/phil/scala/s-99/p22.scala
  // The classic functional approach would be to use `unfoldr`, which Scala
  // doesn't have.  So we'll write one and then use it.
  def unfoldRight[A, B](s: B)(f: B => Option[(A, B)]): List[A] =
  f(s) match {
    case None => Nil
    case Some((r, n)) => r :: unfoldRight(n)(f)
  }

  def range_FP(start: Int, end: Int): List[Int] =
    unfoldRight(start) { n => if (n > end) None else Some((n, n + 1)) }

}

