import scala.collection.mutable

/**
  * Lists are immutable (unlike Arrays)
  * Lists are represented as linked lists
  */

/** DECLARE (immutable) **/

val list1 = List(1, 2, 3, 4, 5, 6, 7, 8, 9)
val list2 = List(1, "a", false)
val list3 = 1 :: 2 :: 3 :: Nil

/** DECLARE (mutable) **/

val mutList1 = mutable.ArrayBuffer(1, 2, 3, 4, 5, 6, 7, 8, 9) // analogous to java.util.ArrayList
val mutList2 = mutable.ListBuffer(1, 2, 3, 4, 5, 6, 7, 8, 9) // mutable partner of immutable List (implemented as Linked List)

/** CREATE (other ways) **/

val createRes1 = (1 to 9).toList

/** ACCESS **/

list1(1)
list1.head // unsafe, can result in IndexOutOfBoundsException
list1.headOption // safe, Option
list1.tail // List of elements after head

mutList1(1)


/** MUTATE (mutable only) **/

mutList1(1) = 42

/** CONCAT (immutable only) **/

// concatenation creates new list, does not modify original
val concat1 = list1 ++ list2
val concat2 = list1 ::: list2 // same thing
val prepend1 = 0 +: list1
val prepend2 = 0 :: list1 // same thing

/** ADD **/

// immutable
val addRes1: List[Int] = list1 :+ 10

// mutable: (modifies original)
mutList1 += 10
mutList1 ++= List(11, 12)

/** REMOVE **/

// immutable
// ???

// mutable: (modifies original)
mutList1 -= 4
mutList1 --= List(5, 6)
// mutable: (returns new coll)
val remove1 = mutList1 - 1
val remove2 = mutList1 -- List(2, 3)

/** distinct **/
// remove duplicated elements

val distinctRes1 = List(1, 2, 3, 4, 1).distinct

/** diff **/

val diffList1 = List(1, 2, 3, 4) diff List(1, 3)

/** find **/

val personList = List(("Alice", 1), ("Bob", 2), ("Carol", 3))
def findByName(name: String) = personList.find(_._1 == name).getOrElse(("David", 4))

/** filter, filterNot **/

val filterRes1 = list1.filter(_ > 3)
val filterNotRes1 = list1.filterNot(_ <= 3)
filterRes1 == filterNotRes1 //> true // contents are identical

/** reverse, reverse_::: **/

val reverseRes1 = list1.reverse
val reverseRes2 = list1.reverse_:::(List(0, -1, -2))

/** map **/

val mapRes2 = list1.map(_ * 2)

/** reduce **/

val reduceRes1 = list1.reduce(_ + _) // calls reduceLeft
val reduceLeftRes1 = list1.reduceLeft(_ + _)
val reduceRightRes1 = list1.reduceRight(_ + _)

val reduceOptionRes1 = list1.reduceOption(_ + _) // calls reduceLeftOption
val reduceLeftOptionRes1 = list1.reduceLeftOption(_ + _)
val reduceRightOptionRes1 = list1.reduceRightOption(_ + _)

/** sum **/

val sumRes1 = list1.sum

/** product **/

val productRes1 = list1.product

/** fold **/

val foldRes1 = list1.fold(0)(_ + _) // calls foldLeft
val foldLeftRes1 = list1.foldLeft(0)(_ + _)
val foldRightRes1 = list1.foldRight(0)(_ + _)


/**
  * FROM COURSERA
  * progfun1, lecture 4.7
  */

// All operations on lists can be expressed in terms of 3 basic operations:
// head, tail, isEmpty

List(1, 2, 3)
1 :: 2 :: 3 :: Nil
1 :: (2 :: (3 :: Nil))
// :: operator associates to the RIGHT, as all operators that end with ":" in Scala
// That is why if you write
// 1 :: 2 :: 3
// it will not compile, as 2 is trying to add a list to the end and 3 is not a list, while
// 3 :: Nil
// is a list and Nil is an empty list
// Also! Operators ending in ":" are seen as method calls of RIGHT-HAND operand,
// so expression above is really expanded to:
Nil.::(3).::(2).::(1)

// Insertion sort implementation using Lists and PM
def isort(xs: List[Int]): List[Int] = xs match {
  case Nil => Nil
  case h :: t => insert(h, isort(t))
}
def insert(x: Int, xs: List[Int]): List[Int] = xs match {
  case Nil => List(x)
  case h :: t => if (h > x) x :: xs else h :: insert(x, t)
}
