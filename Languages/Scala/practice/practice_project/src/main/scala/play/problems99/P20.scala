package play.problems99

import java.util.NoSuchElementException

import scala.annotation.tailrec

object Main20 extends App {

  println("removeAt")
  println(P20.removeAt(1, List('a, 'b, 'c, 'd)))
  // (List[Symbol], Symbol) = (List('a, 'c, 'd),'b)

  println("removeAt_R")
  println(P20.removeAt_R(1, List('a, 'b, 'c, 'd)))
  // (List[Symbol], Symbol) = (List('a, 'c, 'd),'b)

  println("removeAt_TR")
  println(P20.removeAt_TR(1, List('a, 'b, 'c, 'd)))
  // (List[Symbol], Symbol) = (List('a, 'c, 'd),'b)

  println("removeAt_2")
  println(P20.removeAt_2(1, List('a, 'b, 'c, 'd)))
  // (List[Symbol], Symbol) = (List('a, 'c, 'd),'b)

  println("removeAt_3")
  println(P20.removeAt_3(1, List('a, 'b, 'c, 'd)))
  // (List[Symbol], Symbol) = (List('a, 'c, 'd),'b)

  println("removeAt_R_2")
  println(P20.removeAt_R_2(1, List('a, 'b, 'c, 'd)))
  // (List[Symbol], Symbol) = (List('a, 'c, 'd),'b)

}

/**
  * Remove the Kth element from a list.
  */
object P20 {

  def removeAt[T](n: Int, ls: List[T]) = {
    if (n < 0 || n >= ls.size) throw new NoSuchElementException
    val take = ls.take(n + 1)
    (take.init ++ ls.drop(n + 1), take.last)
  }

  def removeAt_R[T](n: Int, ls: List[T]): (List[T], T) = {
    if (n < 0 || n >= ls.size) throw new NoSuchElementException
    if (n == 0) (ls.tail, ls.head)
    else {
      val (t, removed) = removeAt_R(n - 1, ls.tail)
      (ls.head :: t, removed)
    }
  }

  def removeAt_TR[T](n: Int, ls: List[T]) = {
    if (n < 0 || n >= ls.size) throw new NoSuchElementException
    @tailrec def removeAt_TR_acc(n: Int, p1: List[T], p2: List[T]): (List[T], T) = {
      if (n == 0) (p1 ++ p2.tail, p2.head)
      else removeAt_TR_acc(n - 1, p1 :+ p2.head, p2.tail)
    }
    removeAt_TR_acc(n, Nil, ls)
  }

  // Not mine. From: http://aperiodic.net/phil/scala/s-99/p20.scala
  def removeAt_2[A](n: Int, ls: List[A]): (List[A], A) = ls.splitAt(n) match {
    case _ if n < 0 => throw new NoSuchElementException
    case (pre, e :: post) => (pre ::: post, e)
    case (_, Nil) => throw new NoSuchElementException
  }

  def removeAt_3[A](n: Int, ls: List[A]): (List[A], A) = {
    if (n < 0 || n >= ls.size) throw new NoSuchElementException
    else {
      val (p1, p2) = ls.splitAt(n)
      (p1 ++ p2.tail, p2.head)
    }
  }

  // Not mine. From: http://aperiodic.net/phil/scala/s-99/p20.scala
  def removeAt_R_2[A](n: Int, ls: List[A]): (List[A], A) = {
    if (n < 0) throw new NoSuchElementException
    else (n, ls) match {
      case (_, Nil) => throw new NoSuchElementException
      case (0, h :: tail) => (tail, h)
      case (_, h :: tail) =>
        val (t, e) = removeAt_R_2(n - 1, ls.tail)
        (ls.head :: t, e)
    }
  }

}

