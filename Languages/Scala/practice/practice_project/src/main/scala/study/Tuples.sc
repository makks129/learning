/** DECLARE **/

val t1 = (1, "hello", Console)

/** DECLARE VARIABLES USING TUPLES **/

val (double, bool, list) = (2.0, true, List)
val (num, str, cons) = t1

/** ACCESS **/

t1._1
t1._2
t1._3

// To access tuple which is a function argument use PM
List((1, "a"), (2, "b")) map { case (n, s) => n + s } // List(1b, 2b)

/** SWAP **/

val t2 = (1, "hello").swap
t2._1 //> "hello"
t2._2 //> 1