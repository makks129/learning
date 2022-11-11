package play.problems99

object Main11 extends App {

  println("encodeModified_P09")
  println(P11.encodeModified_P09(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)))
  // List[Any] = List((4,'a), 'b, (2,'c), (2,'a), 'd, (4,'e))
  println("encodeModified_P10")
  println(P11.encodeModified_P10(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)))
  // List[Any] = List((4,'a), 'b, (2,'c), (2,'a), 'd, (4,'e))
  println("encodeModified_P10_E")
  println(P11.encodeModified_P10_E(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)))
  // List(Right((4,'a)), Left('b), Right((2,'c)), Right((2,'a)), Left('d), Right((4,'e)))

}

/**
  * Modified run-length encoding.
  */
object P11 {

  def encodeModified_P09[T](ls: List[T]) = {
    P09.pack_TR(ls) map { l => if (l.size == 1) l.head else (l.size, l.head) }
  }

  def encodeModified_P10[T](ls: List[T]) = {
    P10.encode_TR(ls) map { t => if (t._1 == 1) t._2 else t }
  }

  // Typesafe version (using Either)
  // Not mine. From: http://aperiodic.net/phil/scala/s-99/p11.scala
  def encodeModified_P10_E[A](ls: List[A]): List[Either[A, (Int, A)]] =
  P10.encode_TR(ls) map { t => if (t._1 == 1) Left(t._2) else Right(t) }

}

