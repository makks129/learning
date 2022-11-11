package play.problems99

import scala.annotation.tailrec

object Main14 extends App {

  println("duplicate_FP")
  println(P14.duplicate_FP(List('a, 'b, 'c, 'c, 'd)))
  // List('a, 'a, 'b, 'b, 'c, 'c, 'c, 'c, 'd, 'd)
  println("duplicate_FP_2")
  println(P14.duplicate_FP_2(List('a, 'b, 'c, 'c, 'd)))
  // List('a, 'a, 'b, 'b, 'c, 'c, 'c, 'c, 'd, 'd)
  println("duplicate_R")
  println(P14.duplicate_R(List('a, 'b, 'c, 'c, 'd)))
  // List('a, 'a, 'b, 'b, 'c, 'c, 'c, 'c, 'd, 'd)
  println("duplicate_R_PM")
  println(P14.duplicate_R_PM(List('a, 'b, 'c, 'c, 'd)))
  // List('a, 'a, 'b, 'b, 'c, 'c, 'c, 'c, 'd, 'd)
  println("duplicate_TR")
  println(P14.duplicate_TR(List('a, 'b, 'c, 'c, 'd)))
  // List('a, 'a, 'b, 'b, 'c, 'c, 'c, 'c, 'd, 'd)
  println("duplicate_TR_PM")
  println(P14.duplicate_TR_PM(List('a, 'b, 'c, 'c, 'd)))
  // List('a, 'a, 'b, 'b, 'c, 'c, 'c, 'c, 'd, 'd)

}

/**
  * Duplicate the elements of a list.
  */
object P14 {

  def duplicate_FP[T](ls: List[T]) = {
    ls.flatMap(List.fill(2)(_))
  }

  def duplicate_FP_2[T](ls: List[T]) = {
    ls.foldLeft(List[T]())((res, e) => res ++ List.fill(2)(e))
  }

  def duplicate_R[T](ls: List[T]): List[T] = {
    if (ls.isEmpty) Nil
    else List.fill(2)(ls.head) ++ duplicate_R(ls.tail)
  }

  def duplicate_R_PM[T](ls: List[T]): List[T] = ls match {
    case Nil => Nil
    case h :: tail => List.fill(2)(h) ++ duplicate_R_PM(tail)
  }

  def duplicate_TR[T](ls: List[T]) = {
    @tailrec def duplicate_TR_acc(res: List[T], ls: List[T]): List[T] = {
      if (ls.isEmpty) res
      else duplicate_TR_acc(res ++ List.fill(2)(ls.head), ls.tail)
    }
    duplicate_TR_acc(Nil, ls)
  }

  def duplicate_TR_PM[T](ls: List[T]) = {
    @tailrec def duplicate_TR_acc(res: List[T], ls: List[T]): List[T] = ls match {
      case Nil => res
      case h :: tail => duplicate_TR_acc(res ++ List.fill(2)(h), tail)
    }
    duplicate_TR_acc(Nil, ls)
  }

}

