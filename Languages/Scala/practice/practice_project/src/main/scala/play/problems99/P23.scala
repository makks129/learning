package play.problems99

import scala.annotation.tailrec
import scala.util.Random

object Main23 extends App {

  println(P23.randomSelect_R(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h))) // List('e, 'd, 'a) // random elements
  println(P23.randomSelect_TR(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h))) // List('e, 'd, 'a) // random elements

}

/**
  * Extract a given number of randomly selected elements from a list.
  */
object P23 {

  def randomSelect_R[T](n: Int, ls: List[T]): List[T] = {
    if (n <= 0) Nil
    else {
      val (rest, e) = P20.removeAt_2(Random.nextInt(ls.size), ls)
      e +: randomSelect_R(n - 1, rest)
    }
  }

  // TR with Random instance reuse
  def randomSelect_TR[T](n: Int, ls: List[T]) = {
    @tailrec def randomSelect_TR_acc(n: Int, ls: List[T], res: List[T], r: Random): List[T] = {
      if (n <= 0) res
      else {
        val (rest, e) = P20.removeAt_2(r.nextInt(ls.size), ls)
        randomSelect_TR_acc(n - 1, rest, res :+ e, r)
      }
    }
    randomSelect_TR_acc(n, ls, Nil, new Random)
  }

}

