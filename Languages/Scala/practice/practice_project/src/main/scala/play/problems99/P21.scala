package play.problems99

import scala.annotation.tailrec

object Main21 extends App {

  println("insertAt")
  println(P21.insertAt('new, 0, List('a, 'b, 'c, 'd)))
  println(P21.insertAt('new, 1, List('a, 'b, 'c, 'd)))
  // List('a, 'new, 'b, 'c, 'd)
  println(P21.insertAt('new, 2, List('a, 'b, 'c, 'd)))
  println(P21.insertAt('new, 3, List('a, 'b, 'c, 'd)))
  println(P21.insertAt('new, 4, List('a, 'b, 'c, 'd)))

  println("insertAt_PM")
  println(P21.insertAt_PM('new, 0, List('a, 'b, 'c, 'd)))
  println(P21.insertAt_PM('new, 1, List('a, 'b, 'c, 'd)))
  //   List('a, 'new, 'b, 'c, 'd)
  println(P21.insertAt_PM('new, 2, List('a, 'b, 'c, 'd)))
  println(P21.insertAt_PM('new, 3, List('a, 'b, 'c, 'd)))
  println(P21.insertAt_PM('new, 4, List('a, 'b, 'c, 'd)))

  println("insertAt_2")
  println(P21.insertAt_2('new, 0, List('a, 'b, 'c, 'd)))
  println(P21.insertAt_2('new, 1, List('a, 'b, 'c, 'd)))
  // List('a, 'new, 'b, 'c, 'd)
  println(P21.insertAt_2('new, 2, List('a, 'b, 'c, 'd)))
  println(P21.insertAt_2('new, 3, List('a, 'b, 'c, 'd)))
  println(P21.insertAt_2('new, 4, List('a, 'b, 'c, 'd)))

  println("insertAt_R")
  println(P21.insertAt_R('new, 0, List('a, 'b, 'c, 'd)))
  println(P21.insertAt_R('new, 1, List('a, 'b, 'c, 'd)))
  // List('a, 'new, 'b, 'c, 'd)
  println(P21.insertAt_R('new, 2, List('a, 'b, 'c, 'd)))
  println(P21.insertAt_R('new, 3, List('a, 'b, 'c, 'd)))
  println(P21.insertAt_R('new, 4, List('a, 'b, 'c, 'd)))

  println("insertAt_R_PM")
  println(P21.insertAt_R_PM('new, 0, List('a, 'b, 'c, 'd)))
  println(P21.insertAt_R_PM('new, 1, List('a, 'b, 'c, 'd)))
  // List('a, 'new, 'b, 'c, 'd)
  println(P21.insertAt_R_PM('new, 2, List('a, 'b, 'c, 'd)))
  println(P21.insertAt_R_PM('new, 3, List('a, 'b, 'c, 'd)))
  println(P21.insertAt_R_PM('new, 4, List('a, 'b, 'c, 'd)))

  println("insertAt_TR")
  println(P21.insertAt_TR('new, 0, List('a, 'b, 'c, 'd)))
  println(P21.insertAt_TR('new, 1, List('a, 'b, 'c, 'd)))
  // List('a, 'new, 'b, 'c, 'd)
  println(P21.insertAt_TR('new, 2, List('a, 'b, 'c, 'd)))
  println(P21.insertAt_TR('new, 3, List('a, 'b, 'c, 'd)))
  println(P21.insertAt_TR('new, 4, List('a, 'b, 'c, 'd)))

  println("insertAt_3")
  println(P21.insertAt_3('new, 0, List('a, 'b, 'c, 'd)))
  println(P21.insertAt_3('new, 1, List('a, 'b, 'c, 'd)))
  // List('a, 'new, 'b, 'c, 'd)
  println(P21.insertAt_3('new, 2, List('a, 'b, 'c, 'd)))
  println(P21.insertAt_3('new, 3, List('a, 'b, 'c, 'd)))
  println(P21.insertAt_3('new, 4, List('a, 'b, 'c, 'd)))


}

/**
  * Insert an element at a given position into a list.
  */
object P21 {

  def insertAt[T](e: T, n: Int, ls: List[T]) = {
    if (n < 0 || n > ls.size) throw new IllegalArgumentException
    val (pre, post) = ls.splitAt(n)
    pre ::: e :: post
  }

  def insertAt_PM[T](e: T, n: Int, ls: List[T]) = ls.splitAt(n) match {
    case _ if n < 0 || n > ls.size => throw new IllegalArgumentException
    case (pre, post) => pre ::: e :: post
  }

  def insertAt_2[T](e: T, n: Int, ls: List[T]) = {
    if (n < 0 || n > ls.size) throw new IllegalArgumentException
    ls.take(n) ::: e :: ls.drop(n)
  }

  def insertAt_R[T](e: T, n: Int, ls: List[T]): List[T] = {
    if (n < 0 || n > ls.size) throw new IllegalArgumentException
    if (n == 0) e :: ls
    else ls.head :: insertAt_R(e, n - 1, ls.tail)
  }

  def insertAt_R_PM[T](e: T, n: Int, ls: List[T]): List[T] = (n, ls) match {
    case _ if n < 0 || n > ls.size => throw new IllegalArgumentException
    case (0, l) => e :: l
    case (_, h :: t) => h :: insertAt_R_PM(e, n - 1, t)
  }

  def insertAt_TR[T](e: T, n: Int, ls: List[T]) = {
    if (n < 0 || n > ls.size) throw new IllegalArgumentException
    @tailrec def insertAt_TR_acc(n: Int, res: List[T], ls: List[T]): List[T] = {
      if (n == 0) res ::: e :: ls
      else insertAt_TR_acc(n - 1, res :+ ls.head, ls.tail)
    }
    insertAt_TR_acc(n, Nil, ls)
  }

  def insertAt_3[T](e: T, n: Int, ls: List[T]) = {
    if (n < 0 || n > ls.size) throw new IllegalArgumentException
    if (n == ls.size) ls :+ e
    else ls.zipWithIndex.foldLeft(List[T]()) { (res, t) =>
      if (t._2 == n) res ::: List(e, t._1)
      else res :+ t._1
    }
  }

}

