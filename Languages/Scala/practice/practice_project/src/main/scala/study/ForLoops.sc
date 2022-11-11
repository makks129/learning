// https://www.scala-exercises.org/std_lib/for_expressions

val xValues = 1 to 4
val yValues = 1 to 2
val coordinates = for {
  x <- xValues
  y <- yValues
} yield (x, y) //> Vector((1,1), (1,2), (2,1), (2,2), (3,1), (3,2), (4,1), (4,2))
coordinates(4) //> (3,1)


val nums = List(List(1), List(2), List(3), List(4), List(5))
val ress1 = for {
  numList <- nums
  num <- numList
  if num % 2 == 0
} yield num
val ress2 = nums.flatMap(_ filter (_ % 2 == 0))
val ress3 = nums.flatMap(numList => numList).filter(_ % 2 == 0)
val ress5 = nums.flatten(numList => numList).filter(_ % 2 == 0)
val ress4 = nums.flatten.filter(_ % 2 == 0)


/** Queries with for loops **/

case class Book(title: String, authors: List[String])

val books = List(
  Book(title = "Book One", authors = List("Author1", "Author2")),
  Book(title = "Book Two", authors = List("Author1", "Author3")),
  Book(title = "Book Three", authors = List("Author4", "Author5")))

val booksRes1 =
  for (b <- books; a <- b.authors if a == "Author1") yield b.title // List(Book One, Book Two)

val booksRes2 =
  for {
    b1 <- books
    b2 <- books
    if b1 != b2
    a1 <- b1.authors
    a2 <- b2.authors
    if a1 == a2
  } yield a1 // List(Author1, Author1)


/** Relation to standard collections' methods **/

// NOTE pseudocode!
/*

for (x <- e1) yield e2   // this is the same as...
e1.map(x => e2)          // ...this

for (x <- e1 if f; s) yield e2        // this is the same as...
for (x <- e1.withFilter(x => f); s) yield e2   // ...this

for (x <- e1; y <- e2; s) yield e3
e1.flatMap(x => for (y <- e2; s) yield e3)

*/

/** PM in For loops **/

trait Xx

case class X1(s: String) extends Xx

case class X2(i: Int) extends Xx

case class X3(b: Boolean) extends Xx

val pmInForLs1 = List(X1("s"), X2(1), X3(true))

for {
  X1(s) <- pmInForLs1
  X2(i) <- pmInForLs1
  X3(b) <- pmInForLs1
} yield (s, i, b) // List((s,1,true))

// How it works
// Note: pseudocode
/*

pat <- expr

translates into:

x <- expr withFilter {
  case pat => true
  case _ => false
} map {
  case pat => x
}

*/