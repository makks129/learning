// https://www.scala-exercises.org/std_lib/type_signatures

import util.Random

/**
  * In Java you declare a generic type within a <>, in Scala it is []
  */

/** Type variance **/

// T is superclass of T' .... +T
// T is subclass of T' ...... -T
// T is not related to T' ...  T


/** Variables **/

val list1: List[String] = "a" :: "b" :: "c" :: Nil
val list2 = "a" :: "b" :: "c" :: Nil

// type inferred automatically

/** Traits **/

trait Randomizer[A] { // trait declares a type
  def draw(): A
}

class IntRandomizer extends Randomizer[Int] { // implementer satisfies the type
  def draw() = Random.nextInt()
}


/** Derive class **/

"String".isInstanceOf[String]
classOf[String].getCanonicalName
classOf[String].getSimpleName


/** Function types **/

// Functions are traits
new Function1[String, Any] {
  override def apply(v1: String): Any = {}
}
// Such function type is (String => Any)
// Which means this type can be extended
trait TestMap1[K, V] extends (String => Any)
// declaring separate type for function type
type FuncTypeStringToAny = (String => Any)
// using separate type for function type
trait TestMap2[K, V] extends FuncTypeStringToAny
