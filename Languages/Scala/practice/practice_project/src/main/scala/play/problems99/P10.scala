package play.problems99

import scala.annotation.tailrec

object Main10 extends App {

  println("encode")
  println(P10.encode(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)))
  // List[(Int, Symbol)] = List((4,'a), (1,'b), (2,'c), (2,'a), (1,'d), (4,'e))
  println("encode_2")
  println(P10.encode_2(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)))
  // List[(Int, Symbol)] = List((4,'a), (1,'b), (2,'c), (2,'a), (1,'d), (4,'e))
  println("encode_R")
  println(P10.encode_R(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)))
  // List[(Int, Symbol)] = List((4,'a), (1,'b), (2,'c), (2,'a), (1,'d), (4,'e))
  println("encode_TR")
  println(P10.encode_TR(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)))
  // List[(Int, Symbol)] = List((4,'a), (1,'b), (2,'c), (2,'a), (1,'d), (4,'e))

}

/**
  * Run-length encoding of a list.
  */
object P10 {

  def encode[T](ls: List[T]) = {
    P09.pack_TR(ls).foldLeft(List[(Int, T)]())((res, e) => res :+ (e.size, e.head))
  }

  // Best. Not mine. From http://aperiodic.net/phil/scala/s-99/p10.scala
  def encode_2[T](ls: List[T]) = {
    P09.pack_TR(ls).map(e => (e.size, e.head))
  }

  def encode_R[T](ls: List[T]): List[(Int, T)] = {
    if (ls.isEmpty) Nil
    else {
      val (span, rest) = ls.span(_ == ls.head)
      (span.size, ls.head) +: encode_R(rest)
    }
  }

  def encode_TR[T](ls: List[T]) = {
    @tailrec def encode_TR_acc(res: List[(Int, T)], cur: List[T]): List[(Int, T)] = {
      if (cur.isEmpty) res
      else {
        val (span, rest) = cur.span(_ == cur.head)
        encode_TR_acc(res :+ (span.size, cur.head), rest)
      }
    }
    encode_TR_acc(Nil, ls)
  }

}

