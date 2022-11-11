// https://www.scala-exercises.org/std_lib/traversables
// http://www.decodified.com/scala/collections-api.xml

/**
  * Traversables are the superclass of Lists, Arrays, Maps, Sets, Streams, and more
  *
  */

val list1 = List(1, 3, 5, 10)
val set1 = Set(2, 4, 6, 10)

/** Convert **/

List(1, 2, 3).toSet // will convert any Traversable to a Set which is a collection of unordered, unique values
Set(1, 2, 3).toList // will convert any Traversable to a List
Set(1, 2, 3).toArray // will convert any Traversable to an Array, which is a special wrapper around a primitive Java array
Set(1, 2, 3).toIterable //  will convert any Traversable to an Iterable. This is a base trait for all Scala collections that define an iterator method to step through one-by-one the collection's elements
Set(1, 2, 3).toSeq // will convert any Traversable to a Seq which is an ordered Iterable and is the superclass to Lists, Queues and Vectors. Sequences provide a method apply for indexing
Set(1, 2, 3).toIndexedSeq // will convert any Traversable to an IndexedSeq which is an indexed sequence used in Vectors and Strings
Set(1, 2, 3).toStream // will convert any Traversable to a Stream which is a lazy list where elements are evaluated as they are needed
List("a" -> 1, "b" -> 2).toMap //will convert any Traversable to a Map. How it's used depends on the original collection; if it's a List or Seq, it should be of parametrized type Tuple2

/** Partitioning and sorting **/
// head, headOption
// last, lastOption
// tail, init
// slice
// take, takeWhile
// drop, dropWhile
// filter, filterNot
// splitAt
// span
// partition

val headRes1: Int = list1.head
val headOptionRes1: Option[Int] = list1.headOption
val lastRes1: Int = list1.last
val lastOptionRes1: Option[Int] = list1.lastOption
val tailRes1: List[Int] = list1.tail // collection w/o head
val initRes1: List[Int] = list1.init // collection w/o last element
val sliceRes1: List[Int] = list1.slice(1, 3) // including TO, excluding FROM
val takeRes1: List[Int] = list1.take(2) // collection of first number of elements
val takeWhileRes1: List[Int] = list1.takeWhile(_ < 5) // collection of first number of elements until predicate is no longer satisfied
val dropRes1: List[Int] = list1.drop(2) // collection w/o first number of elements
val dropWhileRes1: List[Int] = list1.dropWhile(_ < 5) // collection where elements are dropped until predicate is no longer satisfied
val filterRes1: List[Int] = list1.filter(_ < 5)
val filterNotRes1: List[Int] = list1.filterNot(_ < 5)
val splitRes1: (List[Int], List[Int]) = list1.splitAt(2)
val spanRes1: (List[Int], List[Int]) = list1.span(_ < 5) // splits collection according to predicate
val partitionRes1: (List[Int], List[Int]) = list1.partition(_ < 5) //  split collection according to predicate. The left hand side contains the elements satisfied by the predicate whereas the right hand side contains those that don't

/** ++ **/

val concatRes1 = list1 ++ set1 //> List(1, 3, 5, 10, 2, 4, 6, 10)
val concatRes2 = set1 ++ list1 //> Set(5, 10, 1, 6, 2, 3, 4)

/** foreach **/
// unlike map foreach return type is Unit, which means it will ignore return of the function and will not return modified collection

list1 foreach (i => print(i * 2))

/** map **/
// map modifies and returns the collection and by applying a function of each element

val mapRes1: List[Int] = list1 map (_ * 2)
val mapRes2: Set[Int] = set1 map (_ * 2)

/** flatten **/

val list2 = List(List(1), List(2, 3, 4), List(5, 6, 7), List(8, 9, 10))
val flattenRes1: List[Int] = list2.flatten

/** flatMap **/

// takes elements from list, applies map to it and then flattens results
val flatMapRes1: List[Int] = list2.flatMap(_.map(_ * 2))
// with Options flatMap will only keep Some and discard all None
// also it will unwrap Some and take its value
val flatMapRes2: List[Int] = list1.flatMap(i => if (i % 2 == 0) Some(i) else None)

/** collect **/
// collect will apply a partial function to all elements of a Traversable and will return a different collection

// with cases (representing partial funcs)
val list3 = List(4, 6, 7, 8, 9, 13, 14)
val collectRes1 = list3.collect {
  case x: Int if x % 2 == 0 => x * 3
  case x: Int if x % 2 != 0 => x * 4
}
// with partial funcs directly
val partialFunc1: PartialFunction[Int, Int] = {
  case x: Int if x % 2 == 0 => x * 3
}
val partialFunc2: PartialFunction[Int, Int] = {
  case x: Int if x % 2 != 0 => x * 4
}
val collectRes2 = list3.collect(partialFunc1 orElse partialFunc2) // same result

/** groupBy **/

val groupByList = List(1, -1, 4, 100, 0)
val groupByRes1: Map[String, List[Int]] = groupByList.groupBy {
  // uses partial functions
  case x: Int if x % 2 != 0 && x < 100 => "Odd and less than 100"
  case x: Int if x != 0 && x % 2 == 0 && x < 100 => "Even and less than 100"
  case x: Int if x < 0 => "Negative Number"
  case x: Int if x > 99 => "Large Number"
  case x: Int if x == 0 => "Zero"
}

/** find **/

val findRes1: Option[Int] = list1 find (_ % 2 == 0)

/** forall **/
// determine if a predicate is valid for all members

val forallRes2: Boolean = list1.forall(_ < 20)
val forallRes1: Boolean = list1.forall(_ < 5)

/** exists **/
// determine if a predicate is valid for some members

val existsRes1: Boolean = list1.exists(_ < 5)
val existsRes2: Boolean = list1.exists(_ > 20)

/** count **/
// count the number of elements that satisfy a predicate

val countRes1: Int = list1.count(_ < 5)
val countRes2: Int = list1.count(_ > 20)

/** sum, product **/

val sumRes1: Int = list1.sum
val productRes1: Int = list1.product

/** min, max **/

val minRes1: Int = list1.min
val maxRes1: Int = list1.max

/** foldLeft, /: **/

val foldLeftList = List(5, 4, 3, 2, 1)
val foldLeftRes1: Int = (0 /: foldLeftList) ((total, next) => total - next) // (((((0 - 5) - 4) - 3) - 2) - 1) = -15
val foldLeftRes2: Int = (0 /: foldLeftList) (_ - _) // same
val foldLeftRes3: Int = foldLeftList.foldLeft(0)((total, next) => total - next) // same
val foldLeftRes4: Int = foldLeftList.foldLeft(0)(_ - _) // same

/** foldRight, :\ **/

val foldRightList = List(5, 4, 3, 2, 1)
val foldRightRes1: Int = (foldRightList :\ 0) ((next, total) => next - total) // (5 - (4 - (3 - (2 - (1 - 0))))) = 3
val foldRightRes2: Int = (foldRightList :\ 0) (_ - _) // same
val foldRightRes3: Int = foldRightList.foldRight(0)((next, total) => next - total) // same
val foldRightRes4: Int = foldRightList.foldRight(0)(_ - _) // same

/** reduceLeft **/
// similar to foldLeft, except that the seed is the head value

val reduceLeftList = List(5, 4, 3, 2, 1)
val reduceLeftRes1: Int = reduceLeftList.reduceLeft(_ - _) // ((((5 - 4) - 3) - 2) - 1) = -5

/** reduceRight **/
// similar to foldRight, except that the seed is the last value

val reduceRightList = List(5, 4, 3, 2, 1)
val reduceRightRes1: Int = reduceRightList.reduceRight(_ - _) // (5 - (4 - (3 - (2 - 1)))) = 3

/** transpose **/
// will take a traversable of traversables and group them by their position in it's own traversable

val transposeList = List(List(1, 2, 3), List("a", "b", "c"), List(true, true, false))
val transposeRes1: List[List[Any]] = transposeList.transpose

