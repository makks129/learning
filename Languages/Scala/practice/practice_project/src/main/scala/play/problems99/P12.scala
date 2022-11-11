package play.problems99

import scala.annotation.tailrec

object Main12 extends App {

  println("decode_FP")
  println(P12.decode_FP(List((4, 'a), (1, 'b), (2, 'c), (2, 'a), (1, 'd), (4, 'e))))
  // List[Symbol] = List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)
  println("decode_FP_2")
  println(P12.decode_FP_2(List((4, 'a), (1, 'b), (2, 'c), (2, 'a), (1, 'd), (4, 'e))))
  // List[Symbol] = List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)
  println("decode_R")
  println(P12.decode_R(List((4, 'a), (1, 'b), (2, 'c), (2, 'a), (1, 'd), (4, 'e))))
  // List[Symbol] = List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)
  println("decode_R_PM")
  println(P12.decode_R_PM(List((4, 'a), (1, 'b), (2, 'c), (2, 'a), (1, 'd), (4, 'e))))
  // List[Symbol] = List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)
  println("decode_TR")
  println(P12.decode_TR(List((4, 'a), (1, 'b), (2, 'c), (2, 'a), (1, 'd), (4, 'e))))
  // List[Symbol] = List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)
  println("decode_TR_PM")
  println(P12.decode_TR_PM(List((4, 'a), (1, 'b), (2, 'c), (2, 'a), (1, 'd), (4, 'e))))
  // List[Symbol] = List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)

}

/**
  * Decode a run-length encoded list.
  */
object P12 {

  def decode_FP[T](ls: List[(Int, T)]) = {
    ls.foldLeft(List[T]()) { (res, t) => res ++ List.fill(t._1)(t._2) }
  }

  def decode_FP_2[T](ls: List[(Int, T)]) = {
    ls flatMap (t => List.fill(t._1)(t._2))
  }

  def decode_R[T](ls: List[(Int, T)]): List[T] = {
    if (ls.isEmpty) Nil
    else List.fill(ls.head._1)(ls.head._2) ++ decode_R(ls.tail)
  }

  def decode_R_PM[T](ls: List[(Int, T)]): List[T] = ls match {
    case Nil => Nil
    case h :: tail => List.fill(h._1)(h._2) ++ decode_R_PM(tail)
  }

  def decode_TR[T](ls: List[(Int, T)]) = {
    @tailrec def decode_TR_acc(res: List[T], ls: List[(Int, T)]): List[T] = {
      if (ls.isEmpty) res
      else decode_TR_acc(res ++ List.fill(ls.head._1)(ls.head._2), ls.tail)
    }
    decode_TR_acc(Nil, ls)
  }

  def decode_TR_PM[T](ls: List[(Int, T)]) = {
    @tailrec def decode_TR_PM_acc(res: List[T], ls: List[(Int, T)]): List[T] = ls match {
      case Nil => res
      case h :: tail => decode_TR_PM_acc(res ++ List.fill(h._1)(h._2), tail)
    }
    decode_TR_PM_acc(Nil, ls)
  }

}

