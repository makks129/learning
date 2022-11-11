package progfun1

package object boolean {

  // Pure Booleans

  object mytrue extends MyBoolean {
    override def ifThenElse[T](t: => T, e: => T): T = t
  }

  object myfalse extends MyBoolean {
    override def ifThenElse[T](t: => T, e: => T): T = e
  }

  abstract class MyBoolean {
    def ifThenElse[T](t: => T, e: => T): T

    /**
      * &&
      * 00 - 0
      * 01 - 0
      * 10 - 0
      * 11 - 1
      */
    def &&(x: MyBoolean): MyBoolean = ifThenElse(x, myfalse)

    /**
      * ||
      * 00 - 0
      * 01 - 1
      * 10 - 1
      * 11 - 1
      */
    def ||(x: MyBoolean): MyBoolean = ifThenElse(mytrue, x)

    /**
      * ^
      * 00 - 0
      * 01 - 1
      * 10 - 1
      * 11 - 0
      */
    def ^(x: MyBoolean): MyBoolean = ifThenElse(x.unary_!, x)

    /**
      * !
      * 0 - 1
      * 1 - 0
      */
    def unary_! : MyBoolean = ifThenElse(myfalse, mytrue)

    /**
      * ==
      * 00 - 1
      * 01 - 0
      * 10 - 0
      * 11 - 1
      */
    def ==(x: MyBoolean): MyBoolean = ifThenElse(x, x.unary_!)

    /**
      * !=
      * 00 - 0
      * 01 - 1
      * 10 - 1
      * 11 - 0
      */
    def !=(x: MyBoolean): MyBoolean = ifThenElse(x.unary_!, x)

    /**
      * <
      * 00 - 0
      * 01 - 1
      * 10 - 0
      * 11 - 0
      */
    def <(x: MyBoolean): MyBoolean = ifThenElse(myfalse, x)

    /**
      * >
      * 00 - 0
      * 01 - 0
      * 10 - 1
      * 11 - 0
      */
    def >(x: MyBoolean): MyBoolean = ifThenElse(x.unary_!, myfalse)
  }

}
