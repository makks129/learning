import scala.collection.immutable.Stream.cons
import scala.math._
import scala.util._

val Tau = Pi * 2
println(s"Happy $Tau Day")
println(s"${
  def x(y: Int) = y * 2
  x(2)
}")

printf("Now you have %.16f problems.", Math.nextAfter(2.0, 3))


1 to 10 by 3 foreach (_ * 2)

val list1to10by3 = (1 to 10 by 3).toList

//Number convenience methods
val num = -5
val numAbs = num.abs //absolute value
val max5or7 = numAbs.max(7)
val min5or7 = numAbs.min(7)
println(numAbs) //5
println(max5or7) //7
println(min5or7) //5


//String operations
val reverse = "Scala".reverse //reverse a string
println(reverse) //alacS
val cap = "scala".capitalize //make first char caps
println(cap) //Scala
val multi = "Scala!" * 7 //repeat n times
println(multi) //Scala!Scala!Scala!Scala!Scala!Scala!Scala!
val int = "123".toInt //parse as Int
println(int)


val range1 = 1 to 10
val range2 = 1 to 10
//Useful methods on collections
//filter - keep only items larger than 4
val moreThan4 = range1.filter(_ > 4)
println(moreThan4) //Vector(5, 6, 7, 8, 9, 10)
//map - transform each item in the collection
val doubleIt = range2.map(_ * 2)
println(doubleIt) //Vector(2, 4, 6, 8, 10, 12, 14, 16, 18)


//a method that requires a function as a parameter
//the function's type is (Int,Int) => Int
//e.g. maps from 2 Ints to an Int
def doWith1And2(f: (Int, Int) => Int) = {
  f(1, 2)
}
// Declare method beforehand
def add(x: Int, y: Int) = x + y
doWith1And2(add)
// Declare anon func, full
doWith1And2((x: Int, y: Int) => {
  val z = x + y
  z + y
})
// Declare anon func, short
doWith1And2((x, y) => x + y)
// Declare anon func, super short
doWith1And2(_ + _)
doWith1And2(_ - _)

def doWith1And2And3(f: (Int, Int, Int) => Int) = {
  f(1, 2, 3)
}
doWith1And2And3(_ - _ - _)

def doWith1to10Range(f: (Seq[Int]) => Int) = {
  f(1 to 10)
}

doWith1to10Range(_.sum)


val test: () => Int = {
  //  val r = Random.nextInt
  () => Random.nextInt
}
val testRes = test()
println(testRes)

val res = List(1, 2).map(i => {
  //if (i % 2 == 0) () => i
  () => 42
})
res(0)()
res(1)()



def add1(x: Int, y: Int) = x + y //method
val add2 = (x: Int, y: Int) => x + y //anonymous function
val add3: (Int, Int) => Int = _ + _ //alternate way
val add4 = (_ + _): (Int, Int) => Int //alternate way, rare

println(add1(42, 13))
println(add2(42, 13))
println(add3(42, 13))
println(add4(42, 13))


def swap(x: String, y: String) = (y, x)
val (a, b) = swap("hello", "world")
println(a, b)

var (x, y, z, c, python, java) = (1, 2, 3, true, false, "no!")
println(x, y, z, c, python, java)

val tup1 = ("a", 1, false)
val (tup11, tup12, tup13) = ("a", 1, false)
println(tup11)


//Loops using for
var sum = 0
for (i <- 0 until 10) {
  sum += i
}
println(sum)

//Or, neither while nor for
(0 until 10).sum


val selection = "One"
selection match {
  case "One" => println("You selected option one!")
  case "Two" => println("You selected option two!")
  case _ => println("You selected something else: ")
}



var (i, j): (Int, Int) = (3, 4)
println(i, j)


class Movie(val name: String, val year: Short)

object Movie {
  def academyAwardBestMoviesForYear(x: Short) = {
    x match {
      case 1930 ⇒ Some(new Movie("All Quiet On the Western Front", 1930))
      case 1931 ⇒ Some(new Movie("Cimarron", 1931))
      case 1932 ⇒ Some(new Movie("Grand Hotel", 1932))
      case _ ⇒ None
    }
  }
}

Movie.academyAwardBestMoviesForYear(1930).get.name



var incrementer = 1
def closure = (x: Int) ⇒ x + incrementer
val result1 = closure(10)
incrementer = 2
val result2 = closure(10)



List("Scala", "Erlang", "Clojure") map (_.length)


abstract class Term

case class Var(name: String) extends Term

case class Fun(arg: String, body: Term) extends Term

case class App(f: Term, v: Term) extends Term

def printTerm(term: Term) {
  term match {
    case Var(n) =>
      print(n)
    case Fun(x, b) =>
      print("^" + x + ".")
      printTerm(b)
    case App(f, v) =>
      Console.print("(")
      printTerm(f)
      print(" ")
      printTerm(v)
      print(")")
  }
}
def isIdentityFun(term: Term): Boolean = term match {
  case Fun(x, Var(y)) if x == y => true
  case _ => false
}
val id = Fun("x", Var("x"))
val t = Fun("x", Fun("y", App(Var("x"), Var("y"))))
printTerm(id)
printTerm(t)
println
println(isIdentityFun(id))
println(isIdentityFun(t))




val xValues = 1 to 4
val yValues = 1 to 2
val coordinates = for {
  x <- xValues
  y <- yValues
} yield (x, y) //> Vector((1,1), (1,2), (2,1), (2,2), (3,1), (3,2), (4,1), (4,2))
coordinates(4) //> (3,1)










val stream = cons(0, Stream(10))
val ints: Stream[Int] = for (i <- stream) yield i


def streamer(v: Int): Stream[Int] = cons(v, streamer(v + 1))
streamer(2)






