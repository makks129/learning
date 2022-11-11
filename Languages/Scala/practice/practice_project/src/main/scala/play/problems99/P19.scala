package play.problems99

import scala.annotation.tailrec

object Main19 extends App {

  println("rotate_TR")
  println(P19.rotate_TR(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)))
  // List('d, 'e, 'f, 'g, 'h, 'i, 'j, 'k, 'a, 'b, 'c)
  println(P19.rotate_TR(-2, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)))
  // List('j, 'k, 'a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i)

  println("rotate_TR_2")
  println(P19.rotate_TR_2(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)))
  // List('d, 'e, 'f, 'g, 'h, 'i, 'j, 'k, 'a, 'b, 'c)
  println(P19.rotate_TR_2(-2, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)))
  // List('j, 'k, 'a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i)

}

/**
  * Rotate a list N places to the left.
  */
object P19 {

  @tailrec def rotate_TR[T](n: Int, ls: List[T]): List[T] = {
    if (n == 0 || ls.isEmpty) ls
    else if (n > 0) rotate_TR(n - 1, ls.tail :+ ls.head)
    else rotate_TR(n + 1, ls.last +: ls.init)
  }

  // Not mine. From: http://aperiodic.net/phil/scala/s-99/p19.scala
  @tailrec def rotate_TR_2[A](n: Int, ls: List[A]): List[A] = {
    val nBounded = if (ls.isEmpty) 0 else n % ls.length
    if (nBounded < 0) rotate_TR_2(nBounded + ls.length, ls)
    else (ls drop nBounded) ::: (ls take nBounded)
  }

}