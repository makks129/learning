package play.problems99

import scala.annotation.tailrec

object Main08 extends App {

  println("compress_FP")
  println(P08.compress_FP(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e))) // List[Symbol] = List('a, 'b, 'c, 'd, 'e)
  println("compress_R")
  println(P08.compress_R(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e))) // List[Symbol] = List('a, 'b, 'c, 'd, 'e)
  println("compress_TR")
  println(P08.compress_TR(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e))) // List[Symbol] = List('a, 'b, 'c, 'd, 'e)
  println("compress_R_2")
  println(P08.compress_R_2(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e))) // List[Symbol] = List('a, 'b, 'c, 'a, 'd, 'e)
  println("compress_TR_2")
  println(P08.compress_TR_2(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e))) // List[Symbol] = List('a, 'b, 'c, 'a, 'd, 'e)
  println("compress_FP_2")
  println(P08.compress_FP_2(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e))) // List[Symbol] = List('a, 'b, 'c, 'a, 'd, 'e)

}

/**
  * Eliminate consecutive duplicates of list elements.
  */
object P08 {

  // I misread the exercise and implemented methods that eliminate all duplicate items in the list
  def compress_FP[T](ls: List[T]) = ls.foldLeft(List[T]())((l, s) => if (l.contains(s)) l else l :+ s)

  // I misread the exercise and implemented methods that eliminate all duplicate items in the list
  def compress_R[T](ls: List[T]): List[T] = ls match {
    case Nil => ls
    case init :+ l => if (init.contains(l)) compress_R(init) else compress_R(init) :+ l
  }

  // I misread the exercise and implemented methods that eliminate all duplicate items in the list
  def compress_TR[T](ls: List[T]) = {
    @tailrec def compress_TR_acc(res: List[T], ls: List[T]): List[T] = ls match {
      case Nil => res
      case h :: tail => if (res.contains(h)) compress_TR_acc(res, tail) else compress_TR_acc(res :+ h, tail)
    }
    compress_TR_acc(List(), ls)
  }

  // Not mine. From: http://aperiodic.net/phil/scala/s-99/p08.scala
  def compress_R_2[T](ls: List[T]): List[T] = ls match {
    case Nil => Nil
    case h :: tail => h :: compress_R_2(tail.dropWhile(_ == h))
  }

  // Mine
  def compress_TR_2[T](ls: List[T]) = {
    @tailrec def compress_TR_2_acc(res: List[T], ls: List[T]): List[T] = ls match {
      case Nil => res
      case h :: tail => compress_TR_2_acc(res :+ h, tail.dropWhile(_ == h))
    }
    compress_TR_2_acc(List[T](), ls)
  }

  // Mine
  def compress_FP_2[T](ls: List[T]) = ls.foldRight(List[T]())((s, l) => if (l.isEmpty || l.head != s) s +: l else l)

}

