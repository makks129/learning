
/**
  * Seq is an ordered Iterable
  * Superclass to List, Queue, Vector
  */

/** DECLARE **/

// Seq
val seq1 = Seq(1, 2, 3)
val seq2 = Array(1,2,3).toSeq
val seq3 = for (v <- 1 to 4) yield v