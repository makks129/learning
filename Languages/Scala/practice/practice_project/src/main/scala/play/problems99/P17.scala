package play.problems99

import scala.annotation.tailrec

object Main17 extends App {

  println("split")
  println(P17.split(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)))
  // (List[Symbol], List[Symbol]) = (List('a, 'b, 'c),List('d, 'e, 'f, 'g, 'h, 'i, 'j, 'k))

  println("split_2")
  println(P17.split_2(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)))
  // (List[Symbol], List[Symbol]) = (List('a, 'b, 'c),List('d, 'e, 'f, 'g, 'h, 'i, 'j, 'k))

  println("split_R_PM")
  println(P17.split_R_PM(0, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)))
  println(P17.split_R_PM(1, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)))
  println(P17.split_R_PM(2, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)))
  println(P17.split_R_PM(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)))
  // (List[Symbol], List[Symbol]) = (List('a, 'b, 'c),List('d, 'e, 'f, 'g, 'h, 'i, 'j, 'k))
  println(P17.split_R_PM(4, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)))
  println(P17.split_R_PM(5, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)))
  println(P17.split_R_PM(6, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)))
  println(P17.split_R_PM(7, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)))
  println(P17.split_R_PM(8, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)))
  println(P17.split_R_PM(9, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)))
  println(P17.split_R_PM(10, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)))
  println(P17.split_R_PM(11, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)))
  println(P17.split_R_PM(12, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)))

  println("split_TR_PM")
  println(P17.split_TR_PM(0, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)))
  println(P17.split_TR_PM(1, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)))
  println(P17.split_TR_PM(2, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)))
  println(P17.split_TR_PM(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)))
  // (List[Symbol], List[Symbol]) = (List('a, 'b, 'c),List('d, 'e, 'f, 'g, 'h, 'i, 'j, 'k))
  println(P17.split_TR_PM(4, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)))
  println(P17.split_TR_PM(5, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)))
  println(P17.split_TR_PM(6, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)))
  println(P17.split_TR_PM(7, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)))
  println(P17.split_TR_PM(8, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)))
  println(P17.split_TR_PM(9, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)))
  println(P17.split_TR_PM(10, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)))
  println(P17.split_TR_PM(11, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)))
  println(P17.split_TR_PM(12, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)))

  println("split_TR_PM_2")
  println(P17.split_TR_PM_2(0, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)))
  println(P17.split_TR_PM_2(1, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)))
  println(P17.split_TR_PM_2(2, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)))
  println(P17.split_TR_PM_2(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)))
  // (List[Symbol], List[Symbol]) = (List('a, 'b, 'c),List('d, 'e, 'f, 'g, 'h, 'i, 'j, 'k))
  println(P17.split_TR_PM_2(4, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)))
  println(P17.split_TR_PM_2(5, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)))
  println(P17.split_TR_PM_2(6, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)))
  println(P17.split_TR_PM_2(7, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)))
  println(P17.split_TR_PM_2(8, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)))
  println(P17.split_TR_PM_2(9, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)))
  println(P17.split_TR_PM_2(10, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)))
  println(P17.split_TR_PM_2(11, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)))
  println(P17.split_TR_PM_2(12, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)))

}

/**
  * Split a list into two parts.
  */
object P17 {

  def split[T](n: Int, ls: List[T]) = ls.splitAt(n)

  def split_2[T](n: Int, ls: List[T]) = (ls.take(n), ls.drop(n))

  // Not mine. From: http://aperiodic.net/phil/scala/s-99/p17.scala
  def split_R_PM[T](n: Int, ls: List[T]): (List[T], List[T]) = (n, ls) match {
    case (_, Nil) => (Nil, Nil)
    case (0, l) => (Nil, l)
    case (n_, h :: t) =>
      val (pre, post) = split_R_PM(n_ - 1, t)
      (h :: pre, post)
  }

  def split_TR_PM[T](n: Int, ls: List[T]) = {
    @tailrec def split_TR_PM_acc(res: (List[T], List[T]), n2: Int, ls2: List[T]): (List[T], List[T]) = (n2, ls2) match {
      case (_, Nil) | (0, _) => res
      case (c, h :: t) => split_TR_PM_acc((res._1 :+ h, t), n2 - 1, t)
    }
    split_TR_PM_acc((Nil, ls), n, ls)
  }

  // Not mine. From: http://aperiodic.net/phil/scala/s-99/p17.scala
  def split_TR_PM_2[A](n: Int, ls: List[A]) = {
    @tailrec def splitR(curN: Int, curL: List[A], pre: List[A]): (List[A], List[A]) = (curN, curL) match {
      case (_, Nil) => (pre.reverse, Nil)
      case (0, list) => (pre.reverse, list)
      case (n, h :: tail) => splitR(n - 1, tail, h :: pre)
    }
    splitR(n, ls, Nil)
  }

}

