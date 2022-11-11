package progfun1

import progfun1.boolean._

package object nat {

  /**
    * Represents natural numbers, i.e. non-negative integers (0, 1, 2, ... n)
    * Not using standard built-in numerical classes, only sub-objects and sub-classes
    *
    * Nat is implemented only through itself and doesn't use primitive
    * This is called Peano numbers
    * https://wiki.haskell.org/Peano_numbers
    */
  abstract class Nat {
    def isZero: MyBoolean

    def predecessor: Nat

    def successor: Nat = new Succ(this)

    def +(that: Nat): Nat

    def -(that: Nat): Nat
  }

  object Zero extends Nat {
    override def isZero: MyBoolean = mytrue

    override def predecessor: Nat = throw new Exception("zero predecessor")

    override def +(that: Nat): Nat = that

    override def -(that: Nat): Nat =
      that.isZero.ifThenElse(this, throw new Exception("negative number"))

    // my implementation - wrong :(
//    override def -(that: Nat): Nat = throw new Exception
  }

  class Succ(n: Nat) extends Nat {
    override def isZero: MyBoolean = myfalse

    override def predecessor: Nat = n

    override def +(that: Nat): Nat = new Succ(n + that)

    // my implementation - wrong :(
//    override def +(that: Nat): Nat =
//      that.isZero.ifThenElse(this, this.successor + that.predecessor)

    // my implementation - correct :)
    override def -(that: Nat): Nat =
      that.isZero.ifThenElse(this, n - that.predecessor)

  }

}
