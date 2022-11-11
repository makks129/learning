package play.problems99

import scala.annotation.tailrec

object Main16 extends App {

  println("drop_R")
  println(P16.drop_R(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j)))
  // List[Symbol] = List('a, 'b, 'd, 'e, 'g, 'h, 'j)
  println(P16.drop_R(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)))
  // List[Symbol] = List('a, 'b, 'd, 'e, 'g, 'h, 'j, 'k)
  println(P16.drop_R(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k, 'l)))
  // List[Symbol] = List('a, 'b, 'd, 'e, 'g, 'h, 'j, 'k)

  println("drop_TR")
  println(P16.drop_TR(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j)))
  // List[Symbol] = List('a, 'b, 'd, 'e, 'g, 'h, 'j)
  println(P16.drop_TR(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)))
  // List[Symbol] = List('a, 'b, 'd, 'e, 'g, 'h, 'j, 'k)
  println(P16.drop_TR(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k, 'l)))
  // List[Symbol] = List('a, 'b, 'd, 'e, 'g, 'h, 'j, 'k)

  println("drop_FP")
  println(P16.drop_FP(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j)))
  // List[Symbol] = List('a, 'b, 'd, 'e, 'g, 'h, 'j)
  println(P16.drop_FP(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)))
  // List[Symbol] = List('a, 'b, 'd, 'e, 'g, 'h, 'j, 'k)
  println(P16.drop_FP(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k, 'l)))
  // List[Symbol] = List('a, 'b, 'd, 'e, 'g, 'h, 'j, 'k)

  println("drop_FP_2")
  println(P16.drop_FP_2(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j)))
  // List[Symbol] = List('a, 'b, 'd, 'e, 'g, 'h, 'j)
  println(P16.drop_FP_2(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)))
  // List[Symbol] = List('a, 'b, 'd, 'e, 'g, 'h, 'j, 'k)
  println(P16.drop_FP_2(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k, 'l)))
  // List[Symbol] = List('a, 'b, 'd, 'e, 'g, 'h, 'j, 'k)

  println("drop_FP_3")
  println(P16.drop_FP_3(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j)))
  // List[Symbol] = List('a, 'b, 'd, 'e, 'g, 'h, 'j)
  println(P16.drop_FP_3(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)))
  // List[Symbol] = List('a, 'b, 'd, 'e, 'g, 'h, 'j, 'k)
  println(P16.drop_FP_3(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k, 'l)))
  // List[Symbol] = List('a, 'b, 'd, 'e, 'g, 'h, 'j, 'k)

  println("drop_R_PM")
  println(P16.drop_R_PM(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j)))
  // List[Symbol] = List('a, 'b, 'd, 'e, 'g, 'h, 'j)
  println(P16.drop_R_PM(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)))
  // List[Symbol] = List('a, 'b, 'd, 'e, 'g, 'h, 'j, 'k)
  println(P16.drop_R_PM(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k, 'l)))
  // List[Symbol] = List('a, 'b, 'd, 'e, 'g, 'h, 'j, 'k)

  println("drop_TR_PM")
  println(P16.drop_TR_PM(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j)))
  // List[Symbol] = List('a, 'b, 'd, 'e, 'g, 'h, 'j)
  println(P16.drop_TR_PM(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)))
  // List[Symbol] = List('a, 'b, 'd, 'e, 'g, 'h, 'j, 'k)
  println(P16.drop_TR_PM(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k, 'l)))
  // List[Symbol] = List('a, 'b, 'd, 'e, 'g, 'h, 'j, 'k)

}

/**
  * Drop every Nth element from a list.
  */
object P16 {

  def drop_R[T](n: Int, ls: List[T]): List[T] = {
    if (ls.isEmpty) Nil
    else {
      val (split, rest) = ls.splitAt(n)
      if (split.size < n) split
      else split.init ++ drop_R(n, rest)
    }
  }

  def drop_TR[T](n: Int, ls: List[T]) = {
    @tailrec def drop_TR_acc(res: List[T], ls: List[T]): List[T] = {
      if (ls.isEmpty) res
      else {
        val (split, rest) = ls.splitAt(n)
        if (split.size < n) res ++ split
        else drop_TR_acc(res ++ split.init, rest)
      }
    }
    drop_TR_acc(Nil, ls)
  }

  def drop_FP[T](n: Int, ls: List[T]) = {
    ls.grouped(n).flatMap(l => if (l.size < n) l else l.init).toList
  }

  def drop_FP_2[T](n: Int, ls: List[T]) = {
    ls.sliding(n - 1, n).flatten.toList
  }

  // Not mine. From: http://aperiodic.net/phil/scala/s-99/p16.scala
  def drop_FP_3[A](n: Int, ls: List[A]) = {
    ls.zipWithIndex filter (t => (t._2 + 1) % n != 0) map (_._1)
  }

  // Not mine. From: http://aperiodic.net/phil/scala/s-99/p16.scala
  def drop_R_PM[A](n: Int, ls: List[A]) = {
    def drop_R_PM_inner(c: Int, curList: List[A]): List[A] = (c, curList) match {
      case (_, Nil) => Nil
      case (1, _ :: tail) => drop_R_PM_inner(n, tail)
      case (_, h :: tail) => h :: drop_R_PM_inner(c - 1, tail)
    }
    drop_R_PM_inner(n, ls)
  }

  // Not mine. From: http://aperiodic.net/phil/scala/s-99/p16.scala
  def drop_TR_PM[A](n: Int, ls: List[A]) = {
    @tailrec def drop_TR_PM_acc(c: Int, curList: List[A], result: List[A]): List[A] = (c, curList) match {
      case (_, Nil) => result.reverse
      case (1, _ :: tail) => drop_TR_PM_acc(n, tail, result)
      case (_, h :: tail) => drop_TR_PM_acc(c - 1, tail, h :: result)
    }
    drop_TR_PM_acc(n, ls, Nil)
  }


}

