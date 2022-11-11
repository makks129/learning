package play.problems99

import scala.annotation.tailrec

object Main06 extends App {

  println("isPalindrome_TR")
  println(P06.isPalindrome_TR(List(1, 2, 2, 1))) // true
  println(P06.isPalindrome_TR(List(1, 2, 2, 9))) // false
  println(P06.isPalindrome_TR(List(1, 2, 3, 2, 1))) // true
  println(P06.isPalindrome_TR(List(1, 2, 3, 2, 9))) // false
  println(P06.isPalindrome_TR(List(0, 1, 2, 2, 3, 0))) // false
  println("isPalindrome_TR_PM")
  println(P06.isPalindrome_TR_PM(List(1, 2, 2, 1))) // true
  println(P06.isPalindrome_TR_PM(List(1, 2, 2, 9))) // false
  println(P06.isPalindrome_TR_PM(List(1, 2, 3, 2, 1))) // true
  println(P06.isPalindrome_TR_PM(List(1, 2, 3, 2, 9))) // false
  println(P06.isPalindrome_TR_PM(List(0, 1, 2, 2, 3, 0))) // false
  println("isPalindrome_R")
  println(P06.isPalindrome_R(List(1, 2, 2, 1))) // true
  println(P06.isPalindrome_R(List(1, 2, 2, 9))) // false
  println(P06.isPalindrome_R(List(1, 2, 3, 2, 1))) // true
  println(P06.isPalindrome_R(List(1, 2, 3, 2, 9))) // false
  println(P06.isPalindrome_R(List(0, 1, 2, 2, 3, 0))) // false
  println("isPalindrome_R_PM")
  println(P06.isPalindrome_R_PM(List(1, 2, 2, 1))) // true
  println(P06.isPalindrome_R_PM(List(1, 2, 2, 9))) // false
  println(P06.isPalindrome_R_PM(List(1, 2, 3, 2, 1))) // true
  println(P06.isPalindrome_R_PM(List(1, 2, 3, 2, 9))) // false
  println(P06.isPalindrome_R_PM(List(0, 1, 2, 2, 3, 0))) // false
  println("isPalindrome_R_Ints")
  println(P06.isPalindrome_R_Ints(List(1, 2, 2, 1))) // true
  println(P06.isPalindrome_R_Ints(List(1, 2, 2, 9))) // false
  println(P06.isPalindrome_R_Ints(List(1, 2, 3, 2, 1))) // true
  println(P06.isPalindrome_R_Ints(List(1, 2, 3, 2, 9))) // false
  println(P06.isPalindrome_R_Ints(List(0, 1, 2, 2, 3, 0))) // false

}

/**
  * Find out whether a list is a palindrome.
  */
object P06 {

  def isPalindrome[T](ls: List[T]) = ls == ls.reverse

  @tailrec def isPalindrome_TR[T](ls: List[T]): Boolean = {
    if (ls.size <= 1) true
    else if (ls.head == ls.last) isPalindrome_TR(ls.tail take (ls.tail.size - 1))
    else false
  }

  @tailrec def isPalindrome_TR_PM[T](ls: List[T]): Boolean = ls match {
    case Nil => true
    case h :: Nil => true
    case h :: tail if h == tail.last => isPalindrome_TR_PM(tail take (tail.size - 1))
    case _ => false
  }

  def isPalindrome_R[T](ls: List[T]): Boolean = {
    if (ls.size <= 1) true
    else ls.head == ls.last & isPalindrome_R(ls.tail take (ls.tail.size - 1))
  }

  def isPalindrome_R_PM[T](ls: List[T]): Boolean = ls match {
    case Nil => true
    case h :: Nil => true
    case h :: tail => h == tail.last & isPalindrome_R_PM(tail take (tail.size - 1))
  }

  // for Ints only, because requires summation of elements
  @tailrec def isPalindrome_R_Ints(ls: List[Int]): Boolean = {
    if (ls.size <= 1) true
    else if (ls.size == 2) ls.head == ls.last
    else {
      val lsZipped = (ls take ls.size / 2 + 1) zip (ls takeRight ls.size / 2 + 1) map (t => t._1 + t._2)
      isPalindrome_R_Ints(lsZipped)
    }
  }

}

