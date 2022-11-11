package play.problems99

import play.problems99.common.trampoline._
import scala.annotation.tailrec

object Main04 extends App {

  println(P04.length(List())) // 0
  println(P04.length(List(1))) // 1
  println(P04.length(List(1, 1))) // 2
  println(P04.length(List(1, 1, 2))) // 3
  println()
  println(P04.length_TR(List())) // 0
  println(P04.length_TR(List(1))) // 1
  println(P04.length_TR(List(1, 1))) // 2
  println(P04.length_TR(List(1, 1, 2))) // 3
  println()
  println(P04.length_TR_O(List())) // Some(0)
  println(P04.length_TR_O(List(1))) // Some(1)
  println(P04.length_TR_O(List(1, 1))) // Some(2)
  println(P04.length_TR_O(List(1, 1, 2))) // Some(3)
  println()
  println(P04.length_TR_PM(List())) // 0
  println(P04.length_TR_PM(List(1))) // 1
  println(P04.length_TR_PM(List(1, 1))) // 2
  println(P04.length_TR_PM(List(1, 1, 2))) // 3
  println()
  println(P04.length_TR_PM_O(List())) // Some(0)
  println(P04.length_TR_PM_O(List(1))) // Some(1)
  println(P04.length_TR_PM_O(List(1, 1))) // Some(2)
  println(P04.length_TR_PM_O(List(1, 1, 2))) // Some(3)
  println()
  println(P04.length_R(List())) // 0
  println(P04.length_R(List(1))) // 1
  println(P04.length_R(List(1, 1))) // 2
  println(P04.length_R(List(1, 1, 2))) // 3
  println()
  println(P04.length_R_PM(List())) // 0
  println(P04.length_R_PM(List(1))) // 1
  println(P04.length_R_PM(List(1, 1))) // 2
  println(P04.length_R_PM(List(1, 1, 2))) // 3
  println()
  println(P04.length_L(List())) // 0
  println(P04.length_L(List(1))) // 1
  println(P04.length_L(List(1, 1))) // 2
  println(P04.length_L(List(1, 1, 2))) // 3
  println()
  println(P04.length_FP(List())) // 0
  println(P04.length_FP(List(1))) // 1
  println(P04.length_FP(List(1, 1))) // 2
  println(P04.length_FP(List(1, 1, 2))) // 3

  println()
  println(P04.length_TR((1 to 1000000).toList)) // tail-rec method will not cause SOE
  println(trampoline(P04.length_TRPL((1 to 1000000).toList))) // trampoline method will not cause SOE
  println(P04.length_R_PM((1 to 1000000).toList)) // rec method will cause SOE

}

/**
  * Find the number of elements of a list.
  *
  * !!!
  * Here I used recursion, tail-recursion, classic loop and FP approach
  * For more info on tail-recursion, scala.annotation.tailrec and trampolines:
  * http://blog.richdougherty.com/2009/04/tail-calls-tailrec-and-trampolines.html
  * https://anadea.info/blog/tail-recursion-in-scala
  *
  */
object P04 {

  def length[T](ls: List[T]) = ls.length

  def length_TR[T](ls: List[T]): Int = {
    @tailrec def rec(c: Int, ls: List[T]): Int =
      if (ls == Nil) c else rec(c + 1, ls.tail)
    rec(0, ls)
  }

  def length_TR_O[T](ls: List[T]): Option[Int] = {
    @tailrec def count(c: Int, ls: List[T]): Int =
      if (ls == Nil) c else count(c + 1, ls.tail)
    Some(count(0, ls))
  }

  def length_TR_PM[T](ls: List[T]): Int = {
    @tailrec def count(c: Int, ls: List[T]): Int = ls match {
      case Nil => c
      case _ :: tail => count(c + 1, tail)
    }
    count(0, ls)
  }

  def length_TR_PM_O[T](ls: List[T]): Option[Int] = {
    @tailrec def count(c: Int, ls: List[T]): Int = ls match {
      case Nil => c
      case _ :: tail => count(c + 1, tail)
    }
    Some(count(0, ls))
  }

  def length_R[T](ls: List[T]): Int =
    if (ls == Nil) 0 else 1 + length_R(ls.tail)

  def length_R_PM[T](ls: List[T]): Int = ls match {
    case Nil => 0
    case _ :: tail => 1 + length_R_PM(tail)
  }

  def length_L[T](ls: List[T]): Int = {
    var c = 0
    var l = ls
    while (l != Nil) {
      c += 1
      l = l.tail
    }
    c
  }

  def length_FP[T](ls: List[T]): Int = ls.foldLeft(0)((c, _) => c + 1)

  /**
    * NOTE:
    * trampoline is not needed here, as inner func is itself tail-recursive!
    * trampoline is used here only for demonstration purpose
    * see real example: http://blog.richdougherty.com/2009/04/tail-calls-tailrec-and-trampolines.html
    */
  def length_TRPL[T](ls: List[T]): Bounce[Int] = {
    def length_TRPL_acc(c: Int, ls: List[T]): Bounce[Int] = {
      if (ls == Nil) Done(c)
      else Call(() => length_TRPL_acc(c + 1, ls.tail))
    }
    length_TRPL_acc(0, ls)
  }

}

