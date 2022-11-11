// https://www.scala-exercises.org/std_lib/infix_prefix_and_postfix_operators
// https://www.scala-exercises.org/std_lib/infix_types

/** Postfix **/
// Only works with methods that take 0 args

object Foo {
  def bar = 1
}

Foo bar

42 toString

/** Infix **/
// Only works with methods that take 1 arg

1 + 2
1.+(2)

/** Prefix **/
// Only works if an object has a method name that starts with unary_

class Stereo {
  def unary_+ = "on"

  def unary_- = "off"

  def unary_! = "toggle"

  def unary_~ = "something_else"
}

val stereo = new Stereo
+stereo
-stereo
!stereo
~stereo

/** Mix **/
// Postfix operators have lower precedence than infix operators
// foo bar baz bam bim
// means
// (foo.bar(baz)).bam(bim)

/** Infix types **/
// An infix type T1 op T2 consists of an infix operator op which gets applied to two type operands T1 and T2. The type is equivalent to the type application op[T1,T2].
// The infix operator op may be an arbitrary identifier, except for *, which is reserved as a postfix modifier denoting a repeated parameter type.

class Person(val name: String) {
  def ♡(person: Person) = new ♡(this, person)
}

class ♡[A, B](val a: A, val b: B)
// infix type. can be used as ♡[A,B] or A ♡ B

def announceCouple1(couple: Person ♡ Person) = {
  couple.a.name + " is in love with " + couple.b.name
}
def announceCouple2(couple: ♡[Person, Person]) = {
  couple.a.name + " is in love with " + couple.b.name
}
val romeo = new Person("Romeo")
val juliet = new Person("Juliet")
announceCouple1(new ♡(romeo, juliet))
announceCouple1(romeo ♡ juliet)
announceCouple2(new ♡(romeo, juliet))
announceCouple2(romeo ♡ juliet)

