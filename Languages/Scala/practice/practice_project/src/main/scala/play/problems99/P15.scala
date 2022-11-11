package play.problems99

import scala.annotation.tailrec

object Main15 extends App {

  println("duplicateN_FP")
  println(P15.duplicateN_FP(3, List('a, 'b, 'c, 'c, 'd)))
  // List('a, 'a, 'a, 'b, 'b, 'b, 'c, 'c, 'c, 'c, 'c, 'c, 'd, 'd, 'd)
  println("duplicateN_FP_2")
  println(P15.duplicateN_FP_2(3, List('a, 'b, 'c, 'c, 'd)))
  // List('a, 'a, 'a, 'b, 'b, 'b, 'c, 'c, 'c, 'c, 'c, 'c, 'd, 'd, 'd)
  println("duplicateN_R")
  println(P15.duplicateN_R(3, List('a, 'b, 'c, 'c, 'd)))
  // List('a, 'a, 'a, 'b, 'b, 'b, 'c, 'c, 'c, 'c, 'c, 'c, 'd, 'd, 'd)
  println("duplicateN_R_PM")
  println(P15.duplicateN_R_PM(3, List('a, 'b, 'c, 'c, 'd)))
  // List('a, 'a, 'a, 'b, 'b, 'b, 'c, 'c, 'c, 'c, 'c, 'c, 'd, 'd, 'd)
  println("duplicateN_TR")
  println(P15.duplicateN_TR(3, List('a, 'b, 'c, 'c, 'd)))
  // List('a, 'a, 'a, 'b, 'b, 'b, 'c, 'c, 'c, 'c, 'c, 'c, 'd, 'd, 'd)
  println("duplicateN_TR_PM")
  println(P15.duplicateN_TR_PM(3, List('a, 'b, 'c, 'c, 'd)))
  // List('a, 'a, 'a, 'b, 'b, 'b, 'c, 'c, 'c, 'c, 'c, 'c, 'd, 'd, 'd)

}

/**
  * Duplicate the elements of a list a given number of times.
  */
object P15 {

  def duplicateN_FP[T](n: Int, ls: List[T]) = {
    ls.flatMap(List.fill(n)(_))
  }

  def duplicateN_FP_2[T](n: Int, ls: List[T]) = {
    ls.foldLeft(List[T]())((res, e) => res ++ List.fill(n)(e))
  }

  def duplicateN_R[T](n: Int, ls: List[T]): List[T] = {
    if (ls.isEmpty) Nil
    else List.fill(n)(ls.head) ++ duplicateN_R(n, ls.tail)
  }

  def duplicateN_R_PM[T](n: Int, ls: List[T]): List[T] = ls match {
    case Nil => Nil
    case h :: tail => List.fill(n)(h) ++ duplicateN_R_PM(n, tail)
  }

  def duplicateN_TR[T](n: Int, ls: List[T]) = {
    @tailrec def duplicateN_TR_acc(res: List[T], ls: List[T]): List[T] = {
      if (ls.isEmpty) res
      else duplicateN_TR_acc(res ++ List.fill(n)(ls.head), ls.tail)
    }
    duplicateN_TR_acc(Nil, ls)
  }

  def duplicateN_TR_PM[T](n: Int, ls: List[T]) = {
    @tailrec def duplicateN_TR_acc(res: List[T], ls: List[T]): List[T] = ls match {
      case Nil => res
      case h :: tail => duplicateN_TR_acc(res ++ List.fill(n)(h), tail)
    }
    duplicateN_TR_acc(Nil, ls)
  }

}

