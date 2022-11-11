import java.util.Random

import scala.util.control.NonFatal

val f: String => String = {
  case "ping" => "pong"
}
// same as
val ff = new Function[String, String] {
  override def apply(v1: String): String = v1 match {
    case "ping" => "pong"
  }
}

f("ping")
ff("ping")
// f("abc") // MatchError
// ff("abc") // MatchError

// To check if the case for f is defined, let's use PartialFunction

val pf: PartialFunction[String, String] = {
  case "ping" => "pong"
}
// same as
val pff = new PartialFunction[String, String] {
  override def apply(v1: String): String = v1 match {
    case "ping" => "pong"
  }

  override def isDefinedAt(x: String): Boolean = x match {
    case "ping" => true
    case _ => false
  }
}

pf.isDefinedAt("ping")
pf.isDefinedAt("abc")
pff.isDefinedAt("ping")
pff.isDefinedAt("abc")


/** Translations of For **/

case class Book(title: String, authors: List[String])

val books = List(
  Book(title = "Book One", authors = List("Author1", "Author2")),
  Book(title = "Book Two", authors = List("Author1", "Author3")),
  Book(title = "Book Three", authors = List("Author4", "Author5")))

val booksRes1 =
  for (b <- books; a <- b.authors if a == "Author1") yield b.title // List(Book One, Book Two)
// translates to:
val bookRes2 =
books.flatMap(b => b.authors.withFilter(a => a == "Author1").map(a => b.title))

// List(Book One, Book Two)


/** Functional Random Generators **/

trait Generator[+T] {
  def generate: T
}

val integers = new Generator[Int] {
  val rand = new java.util.Random

  override def generate = rand.nextInt()
}
val booleans = new Generator[Boolean] {
  override def generate = integers.generate > 0
}
val pairs = new Generator[(Int, Int)] {
  override def generate = (integers.generate, integers.generate)
}


trait Generator2[+T] {
  self =>

  def generate: T

  def map[S](f: T => S): Generator2[S] = new Generator2[S] {
    override def generate: S = f(self.generate)
  }

  def flatMap[S](f: T => Generator2[S]): Generator2[S] = new Generator2[S] {
    override def generate: S = f(self.generate).generate
  }
}

val integers2 = new Generator2[Int] {
  val rand = new java.util.Random

  override def generate: Int = rand.nextInt()
}
val booleans2 = integers2 map (i => i > 0)
val booleans2_2 = for (i <- integers2) yield i > 0

def pairs2[T, U](t: Generator2[T], u: Generator2[U]): Generator2[(T, U)] =
  t flatMap (tt => u map (uu => (tt, uu)))

def single2[T](x: T) = new Generator2[T] {
  override def generate: T = x
}

def choose2(lo: Int, hi: Int): Generator2[Int] =
  for (x <- integers2) yield lo + x % (hi - lo)

def oneOf2[T](xs: T*): Generator2[T] =
  for (idx <- choose2(0, xs.length)) yield xs(idx)

def lists2: Generator2[List[Int]] = for {
  isEmpty <- booleans2
  list <- if (isEmpty) emptyLists2 else nonEmptyLists2
} yield list

def emptyLists2: Generator2[List[Int]] = single2(Nil)

def nonEmptyLists2: Generator2[List[Int]] = for {
  head <- integers2
  tail <- lists2
} yield head :: tail

lists2.generate


trait Tree

case class Inner(left: Tree, right: Tree) extends Tree

case class Leaf(x: Int) extends Tree

def trees2: Generator2[Tree] = for {
  isLeaf <- booleans2
  tree <- if (isLeaf) leafs2 else inners2
} yield tree

def leafs2: Generator2[Tree] = for (x <- integers2) yield Leaf(x)

def inners2: Generator2[Tree] = for {
  l <- trees2
  r <- trees2
} yield Inner(l, r)

trees2.generate


/** Monads **/

case class Success[T](x: T) extends Try[T]

case class Failure(ex: Throwable) extends Try[Nothing]

object Try {
  def apply[T](expr: => T): Try[T] = // CBN here because we need to start calculating result of expr here inside the method and not calculate it before it and just pass the result
    try Success(expr)
    catch {
      case NonFatal(ex) => Failure(ex)
    }
}

abstract class Try[+T] {
  def flatMap[U](f: T => Try[U]): Try[U] = this match {
    case Success(t) => try f(t) catch { case NonFatal(ex) => Failure(ex) }
    case fail: Failure => fail
  }
  def map[U](f: T => U): Try[U] = this match {
    case Success(t) => Try(f(t))
    case fail: Failure => fail
  }
}



