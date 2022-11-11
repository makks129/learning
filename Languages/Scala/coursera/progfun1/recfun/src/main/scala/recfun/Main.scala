package recfun

import scala.annotation.tailrec

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }

  /**
    * Exercise 1
    *
    *
    */
  //       c0 c1 c2 c3 c4
  //  r0   1
  //  r1   1  1
  //  r2   1  2  1
  //  r3   1  3  3  1
  //  r4   1  4  6  4  1

  //          0   1   0
  //        0   1   1   0
  //      0   1   2   1   0
  //    0   1   3   3   1   0
  //  0   1   4   6   4   1   0
  def pascal(c: Int, r: Int): Int = {
    if (c < 0 || c > r) 0
    else if (r == 0) 1
    else pascal(c - 1, r - 1) + pascal(c, r - 1)
  }

  /**
    * Exercise 2
    */
  // fun solution
  def balance(chars: List[Char]): Boolean = {
    val br = List(')', null, '(')
    @tailrec def loop(count: Int, ls: List[Char]): Int = ls match {
      case _ if count < 0 => -1
      case h :: t => loop(count + (if (!br.contains(h)) 0 else br.indexOf(h) - 1), t)
      case _ => count
    }
    loop(0, chars) == 0
  }

  // more classic solution
  def balance2(chars: List[Char]): Boolean = {
    @tailrec def loop(count: Int, ls: List[Char]): Int = ls match {
      case _ if count < 0 => -1
      case h :: t =>
        if (h == '(') loop(count + 1, t)
        else if (h == ')') loop(count - 1, t)
        else loop(count, t)
      case _ => count
    }
    loop(0, chars) == 0
  }

  /**
    * Exercise 3
    */
  // includes corner case when money == 0 in the first call
  def countChange(money: Int, coins: List[Int]) = {
    def countChange_inner(m: Int, c: List[Int]): Int = {
      if (m < 0) 0 else if (m == 0) 1 else c match {
        case Nil => 0
        case h :: t =>
          countChange_inner(m - h, c) +
            (if (t == Nil) 0 else countChange_inner(m, t))
      }
    }
    if (money == 0) 0 else countChange_inner(money, coins)
  }

  // doesn't include corner case when money == 0 in the first call
  def countChange2(money: Int, coins: List[Int]): Int = {
    if (money < 0) 0 else if (money == 0) 1 else coins match {
      case Nil => 0
      case h :: t => countChange2(money - h, coins) + (if (t == Nil) 0 else countChange2(money, t))
    }
  }

}
