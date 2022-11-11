package play.problems99

import scala.annotation.tailrec
import scala.collection.mutable

object Main09 extends App {

  println("pack_FP_MUT")
  println(P09.pack_FP_MUT(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)))
  // List(ListBuffer('a, 'a, 'a, 'a), ListBuffer('b), ListBuffer('c, 'c), ListBuffer('a, 'a), ListBuffer('d), ListBuffer('e, 'e, 'e, 'e))
  println("pack_FP")
  println(P09.pack_FP(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)))
  // List(List('a, 'a, 'a, 'a), List('b), List('c, 'c), List('a, 'a), List('d), List('e, 'e, 'e, 'e))
  println("pack_FP_PM")
  println(P09.pack_FP_PM(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)))
  // List(List('a, 'a, 'a, 'a), List('b), List('c, 'c), List('a, 'a), List('d), List('e, 'e, 'e, 'e))
  println("pack_R")
  println(P09.pack_R(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)))
  // List(List('a, 'a, 'a, 'a), List('b), List('c, 'c), List('a, 'a), List('d), List('e, 'e, 'e, 'e))
  println("pack_TR")
  println(P09.pack_TR(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)))
  // List(List('a, 'a, 'a, 'a), List('b), List('c, 'c), List('a, 'a), List('d), List('e, 'e, 'e, 'e))

}

/**
  * Pack consecutive duplicates of list elements into sublists.
  */
object P09 {

  def pack_FP_MUT[T](ls: List[T]): List[mutable.ListBuffer[T]] = {
    ls.foldLeft(List[mutable.ListBuffer[T]]())((res, e) => {
      if (res.nonEmpty && res.last.contains(e)) {
        res.last += e; res
      }
      else res :+ mutable.ListBuffer(e)
    })
  }

  def pack_FP[T](ls: List[T]): List[List[T]] = {
    ls.foldLeft(List[List[T]]())((res, e) => {
      if (res.nonEmpty && res.last.contains(e)) res
      else res :+ ls.diff(res.flatten).takeWhile(_ == e)
    })
  }

  def pack_FP_PM[T](ls: List[T]): List[List[T]] = {
    ls.foldLeft(List[List[T]]())((res, e) => res match {
      case _ :+ l if l.contains(e) => res
      case _ => res :+ ls.diff(res.flatten).takeWhile(_ == e)
    })
  }

  // Not mine. From: http://aperiodic.net/phil/scala/s-99/p09.scala
  def pack_R[T](ls: List[T]): List[List[T]] = {
    if (ls.isEmpty) Nil
    else {
      val (packed, rest) = ls span (_ == ls.head)
      packed +: pack_R(rest)
    }
  }

  // Mine
  def pack_TR[T](ls: List[T]) = {
    @tailrec def pack_TR_acc(res: List[List[T]], cur: List[T]): List[List[T]] = {
      if (cur.isEmpty) res
      else {
        val (packed, rest) = cur span (_ == cur.head)
        pack_TR_acc(res :+ packed, rest)
      }
    }
    pack_TR_acc(Nil, ls)
  }

}

