// https://www.scala-exercises.org/std_lib/iterables
// http://www.decodified.com/scala/collections-api.xml

/**
  * Seq - trait, immutable, Java: List
  *
  *
  *
  *
  */

Seq(1, 2, 3)


/**
  * List - abs class, immutable, Java: LinkedList
  *
  * concrete implementations: :: and Nil
  * highly optimized
  * fundamental in FP
  * Vector is better choice than List (esp for parallel programming)
  */

List(1, 2, 3)


/**
  * Array - class, mutable, Java: array
  *
  * converted to Java's arrays
  * slower than List
  */

Array(1,2,3)


/** grouped **/

val list1 = List(1,2,3,4,5,6)
val groupedRes1: Iterator[List[Int]] = list1 grouped 3
groupedRes1.next()
groupedRes1.next()

/** sliding **/

val slidingRes1: Iterator[List[Int]] = list1 sliding 3
slidingRes1.next()
slidingRes1.next()
slidingRes1.next()
slidingRes1.next()

val slidingRes2: Iterator[List[Int]] = list1 sliding(3,2)
slidingRes2.next()
slidingRes2.next()

val slidingRes3: Iterator[List[Int]] = list1 sliding(2,3)
slidingRes3.next()
slidingRes3.next()

/** take, takeRight **/

val takeRes1: List[Int] = list1 take 3
val takeRightRes1: List[Int] = list1 takeRight 3

/** drop, dropRight **/

val dropRes1: List[Int] = list1 drop 3
val dropRightRes1: List[Int] = list1 dropRight 3

/** zip, zipAll, zipWithIndex **/

// if one of the Iterables is bigger zip will drop excessive elements
val list2 = List("a", "b", "c", "d", "e", "f")
val zipRes1: List[(Int, String)] = list1 zip list2
val zipRes2: List[(String, Int)] = list2 zip list1

// with zipAll you can provide placeholders for missing elements
val list3 = List("a", "b", "c")
val list4 = List(1,2,3)
val zipAllRes1: List[(Int, String)] = list1 zipAll(list3, -1, "?")
val zipAllRes2: List[(String, Int)] = list2 zipAll(list4, "?", -1)

// zipWithIndex will zip an Iterable with it's integer index
val zipWithIndexRes1: List[(String, Int)] = list3.zipWithIndex


