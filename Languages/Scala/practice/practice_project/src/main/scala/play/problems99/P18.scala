package play.problems99

import scala.annotation.tailrec

object Main18 extends App {

  println("slice")
  println(P18.slice(0, 3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k))) // indices: 0, 1, 2
  // List[Symbol] = List('a, 'b, 'c)
  println(P18.slice(3, 7, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k))) // indices: 3, 4, 5, 6
  // List[Symbol] = List('d, 'e, 'f, 'g)
  println(P18.slice(8, 11, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k))) // indices: 8, 9, 10
  // List[Symbol] = List('i, 'j, 'k)

  println("slice_2")
  println(P18.slice_2(0, 3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k))) // indices: 0, 1, 2
  // List[Symbol] = List('a, 'b, 'c)
  println(P18.slice_2(3, 7, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k))) // indices: 3, 4, 5, 6
  // List[Symbol] = List('d, 'e, 'f, 'g)
  println(P18.slice_2(8, 11, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k))) // indices: 8, 9, 10
  // List[Symbol] = List('i, 'j, 'k)

  println("slice_TR")
  println(P18.slice_TR(0, 3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k))) // indices: 0, 1, 2
  // List[Symbol] = List('a, 'b, 'c)
  println(P18.slice_TR(3, 7, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k))) // indices: 3, 4, 5, 6
  // List[Symbol] = List('d, 'e, 'f, 'g)
  println(P18.slice_TR(8, 11, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k))) // indices: 8, 9, 10
  // List[Symbol] = List('i, 'j, 'k)

  println("slice_TR_PM")
  println(P18.slice_TR_PM(0, 3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k))) // indices: 0, 1, 2
  // List[Symbol] = List('a, 'b, 'c)
  println(P18.slice_TR_PM(3, 7, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k))) // indices: 3, 4, 5, 6
  // List[Symbol] = List('d, 'e, 'f, 'g)
  println(P18.slice_TR_PM(8, 11, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k))) // indices: 8, 9, 10
  // List[Symbol] = List('i, 'j, 'k)

  println("slice_R")
  println(P18.slice_R(0, 3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k))) // indices: 0, 1, 2
  // List[Symbol] = List('a, 'b, 'c)
  println(P18.slice_R(3, 7, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k))) // indices: 3, 4, 5, 6
  // List[Symbol] = List('d, 'e, 'f, 'g)
  println(P18.slice_R(8, 11, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k))) // indices: 8, 9, 10
  // List[Symbol] = List('i, 'j, 'k)

  println("slice_TR_2")
  println(P18.slice_TR_2(0, 3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k))) // indices: 0, 1, 2
  // List[Symbol] = List('a, 'b, 'c)
  println(P18.slice_TR_2(3, 7, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k))) // indices: 3, 4, 5, 6
  // List[Symbol] = List('d, 'e, 'f, 'g)
  println(P18.slice_TR_2(8, 11, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k))) // indices: 8, 9, 10
  // List[Symbol] = List('i, 'j, 'k)

  println("slice_TR_3")
  println(P18.slice_TR_3(0, 3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k))) // indices: 0, 1, 2
  // List[Symbol] = List('a, 'b, 'c)
  println(P18.slice_TR_3(3, 7, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k))) // indices: 3, 4, 5, 6
  // List[Symbol] = List('d, 'e, 'f, 'g)
  println(P18.slice_TR_3(8, 11, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k))) // indices: 8, 9, 10
  // List[Symbol] = List('i, 'j, 'k)

  println("slice_TR_4")
  println(P18.slice_TR_4(0, 3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k))) // indices: 0, 1, 2
  // List[Symbol] = List('a, 'b, 'c)
  println(P18.slice_TR_4(3, 7, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k))) // indices: 3, 4, 5, 6
  // List[Symbol] = List('d, 'e, 'f, 'g)
  println(P18.slice_TR_4(8, 11, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k))) // indices: 8, 9, 10
  // List[Symbol] = List('i, 'j, 'k)


}

/**
  * Extract a slice from a list.
  */
object P18 {

  def slice[T](from: Int, until: Int, ls: List[T]) = ls.slice(from, until)

  def slice_2[T](from: Int, until: Int, ls: List[T]) = ls.drop(from).take(until - (from max 0))

  @tailrec def slice_TR[T](from: Int, until: Int, ls: List[T]): List[T] = {
    if (ls.size > until) slice_TR(from, until, ls.init)
    else if (from > 0) slice_TR(from - 1, until, ls.tail)
    else ls
  }

  @tailrec def slice_TR_PM[T](from: Int, until: Int, ls: List[T]): List[T] = (from, until, ls) match {
    case (f, u, l) if l.size > u => slice_TR_PM(f, u, l.init)
    case (f, u, l) if f > 0 => slice_TR_PM(f - 1, u, l.tail)
    case _ => ls
  }

  // Not mine. From: http://aperiodic.net/phil/scala/s-99/p18.scala
  // >> 3, 7, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)
  // case4 => 2, 6, List('b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)
  // case4 => 1, 5, List('c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)
  // case4 => 0, 4, List('d, 'e, 'f, 'g, 'h, 'i, 'j, 'k)
  // case3 => 'd :: (0, 3, List('e, 'f, 'g, 'h, 'i, 'j, 'k))
  // case3 => 'd :: 'e :: (0, 2, List('f, 'g, 'h, 'i, 'j, 'k))
  // case3 => 'd :: 'e :: 'f :: (0, 1, List('g, 'h, 'i, 'j, 'k))
  // case3 => 'd :: 'e :: 'f :: 'g :: (0, 0, List('h, 'i, 'j, 'k))
  // case2 => 'd :: 'e :: 'f :: 'g :: Nil
  // << List('d, 'e, 'f, 'g)
  def slice_R[T](from: Int, until: Int, ls: List[T]): List[T] = (from, until, ls) match {
    /*1*/ case (_, _, Nil) => Nil
    /*2*/ case (_, u, _) if u <= 0 => Nil
    /*3*/ case (f, u, h :: t) if f <= 0 => h :: slice_R(0, u - 1, t)
    /*4*/ case (f, u, h :: t) => slice_R(f - 1, u - 1, t)
  }

  def slice_TR_2[T](from: Int, until: Int, ls: List[T]) = {
    @tailrec def slice_TR_2_acc(from: Int, until: Int, res: List[T], ls: List[T]): List[T] = (from, until, ls) match {
      case (_, _, Nil) => res
      case (_, u, _) if u <= 0 => res
      case (f, u, h :: t) if f <= 0 => slice_TR_2_acc(0, u - 1, res :+ h, t)
      case (f, u, h :: t) => slice_TR_2_acc(f - 1, u - 1, res, t)
    }
    slice_TR_2_acc(from, until, Nil, ls)
  }

  // Not mine. From: http://aperiodic.net/phil/scala/s-99/p18.scala
  def slice_TR_3[A](start: Int, end: Int, ls: List[A]) = {
    @tailrec def slice_TR_3_acc(count: Int, curList: List[A], result: List[A]): List[A] =
      (count, curList) match {
        case (_, Nil) => result.reverse
        case (c, h :: tail) if end <= c => result.reverse
        case (c, h :: tail) if start <= c => slice_TR_3_acc(c + 1, tail, h :: result)
        case (c, _ :: tail) => slice_TR_3_acc(c + 1, tail, result)
      }
    slice_TR_3_acc(0, ls, Nil)
  }

  // Not mine. From: http://aperiodic.net/phil/scala/s-99/p18.scala
  // slice_TR_3 condensed
  def slice_TR_4[A](start: Int, end: Int, ls: List[A]) = {
    @tailrec def slice_TR_4_acc(count: Int, curList: List[A], result: List[A]): List[A] = {
      if (curList.isEmpty || count >= end) result.reverse
      else slice_TR_4_acc(count + 1, curList.tail,
        if (count >= start) curList.head :: result
        else result)
    }
    slice_TR_4_acc(0, ls, Nil)
  }

}

