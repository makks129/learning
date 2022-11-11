package play.problems99

import scala.annotation.tailrec

object Main05 extends App {

  println(P05.reverse(List(1))) // List(1)
  println(P05.reverse(List(1, 1, 2, 3, 5, 8))) // List(8, 5, 3, 2, 1, 1)
  println()
  println(P05.reverse_R(List(1))) // List(1)
  println(P05.reverse_R(List(1, 1, 2, 3, 5, 8))) // List(8, 5, 3, 2, 1, 1)
  println()
  println(P05.reverse_R_PM(List(1))) // List(1)
  println(P05.reverse_R_PM(List(1, 1, 2, 3, 5, 8))) // List(8, 5, 3, 2, 1, 1)
  println()
  println(P05.reverse_TR(List(1))) // List(1)
  println(P05.reverse_TR(List(1, 1, 2, 3, 5, 8))) // List(8, 5, 3, 2, 1, 1)
  println()
  println(P05.reverse_TR_PM(List(1))) // List(1)
  println(P05.reverse_TR_PM(List(1, 1, 2, 3, 5, 8))) // List(8, 5, 3, 2, 1, 1)
  println()
  println(P05.reverse_FP(List(1))) // List(1)
  println(P05.reverse_FP(List(1, 1, 2, 3, 5, 8))) // List(8, 5, 3, 2, 1, 1)

}

/**
  * Reverse a list.
  */
object P05 {

  def reverse[T](ls: List[T]) = ls.reverse

  def reverse_R[T](ls: List[T]): List[T] =
    if (ls == Nil) ls else reverse_R(ls.tail) :+ ls.head

  def reverse_R_PM[T](ls: List[T]): List[T] = ls match {
    case Nil => ls
    case h :: tail => reverse_R_PM(tail) :+ h
  }

  def reverse_TR[T](ls: List[T]): List[T] = {
    @tailrec def reverse_TR_acc(res: List[T], ls: List[T]): List[T] =
      if (ls == Nil) res else reverse_TR_acc(ls.head :: res, ls.tail)
    reverse_TR_acc(Nil, ls)
  }

  def reverse_TR_PM[T](ls: List[T]): List[T] = {
    @tailrec def reverse_TR_PM_acc(res: List[T], ls: List[T]): List[T] = ls match {
      case Nil => res
      case h :: tail => reverse_TR_PM_acc(h :: res, tail)
    }
    reverse_TR_PM_acc(Nil, ls)
  }

  def reverse_FP[T](ls: List[T]): List[T] = {
    ls.foldLeft(List[T]())((res, h) => h :: res)
//    ls.foldLeft[List[T]](Nil)((res, h) => h :: res) // same but with Nil
  }

}

