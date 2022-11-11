// https://www.scala-exercises.org/std_lib/traversables

import scala.collection.immutable.Stream.cons

val stream = cons(0, Stream(10))
val ints: Stream[Int] = for (i <- stream) yield i


def streamer(v: Int): Stream[Int] = cons(v, streamer(v + 1))
streamer(2)


