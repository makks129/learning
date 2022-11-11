package play.problems99

import scala.annotation.tailrec

object Main13 extends App {

  println("encodeDirect_R")
  println(P13.encodeDirect_R(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)))
  // List[(Int, Symbol)] = List((4,'a), (1,'b), (2,'c), (2,'a), (1,'d), (4,'e))
  println("encodeDirect_TR")
  println(P13.encodeDirect_TR(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)))
  // List[(Int, Symbol)] = List((4,'a), (1,'b), (2,'c), (2,'a), (1,'d), (4,'e))

}

/**
  * Run-length encoding of a list (direct solution).
  *
  * Note: already implemented same thing in P10 by mistake
  */
object P13 {

  def encodeDirect_R[T](ls: List[T]): List[(Int, T)] = {
    if (ls.isEmpty) Nil
    else {
      val (span, rest) = ls.span(_ == ls.head)
      (span.size, ls.head) +: encodeDirect_R(rest)
    }
  }

  def encodeDirect_TR[T](ls: List[T]) = {
    @tailrec def encodeDirect_TR_acc(res: List[(Int, T)], cur: List[T]): List[(Int, T)] = {
      if (cur.isEmpty) res
      else {
        val (span, rest) = cur.span(_ == cur.head)
        encodeDirect_TR_acc(res :+ (span.size, cur.head), rest)
      }
    }
    encodeDirect_TR_acc(Nil, ls)
  }

}

