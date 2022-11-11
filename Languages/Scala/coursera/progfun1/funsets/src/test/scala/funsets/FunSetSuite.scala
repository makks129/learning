package funsets

import org.scalatest.FunSuite


import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

/**
 * This class is a test suite for the methods in object FunSets. To run
 * the test suite, you can either:
 *  - run the "test" command in the SBT console
 *  - right-click the file in eclipse and chose "Run As" - "JUnit Test"
 */
@RunWith(classOf[JUnitRunner])
class FunSetSuite extends FunSuite {

  /**
   * Link to the scaladoc - very clear and detailed tutorial of FunSuite
   *
   * http://doc.scalatest.org/1.9.1/index.html#org.scalatest.FunSuite
   *
   * Operators
   *  - test
   *  - ignore
   *  - pending
   */

  /**
   * Tests are written using the "test" operator and the "assert" method.
   */
  // test("string take") {
  //   val message = "hello, world"
  //   assert(message.take(5) == "hello")
  // }

  /**
   * For ScalaTest tests, there exists a special equality operator "===" that
   * can be used inside "assert". If the assertion fails, the two values will
   * be printed in the error message. Otherwise, when using "==", the test
   * error message will only say "assertion failed", without showing the values.
   *
   * Try it out! Change the values so that the assertion fails, and look at the
   * error message.
   */
  // test("adding ints") {
  //   assert(1 + 2 === 3)
  // }


  import FunSets._

  test("contains is implemented") {
    assert(contains(x => true, 100))
  }

  /**
   * When writing tests, one would often like to re-use certain values for multiple
   * tests. For instance, we would like to create an Int-set and have multiple test
   * about it.
   *
   * Instead of copy-pasting the code for creating the set into every test, we can
   * store it in the test class using a val:
   *
   *   val s1 = singletonSet(1)
   *
   * However, what happens if the method "singletonSet" has a bug and crashes? Then
   * the test methods are not even executed, because creating an instance of the
   * test class fails!
   *
   * Therefore, we put the shared values into a separate trait (traits are like
   * abstract classes), and create an instance inside each test method.
   *
   */

  trait TestSets {
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)
    val intersect1 = intersect(_ > 9, _ < 11)
    val diff1 = diff(_ > 9, _ < 11)
    val filter1 = filter(_ > 0, x => x > 5 && x < 10)
    val s4: Set = _ > 0
    val s5: Set = _ > 1100
    val s6: Set = _ < -1100
    val s7: Set = x => x > 0 && x < 10
    val p1: Int => Boolean = _ > -10  // -10 -0-> n
    val p2: Int => Boolean = _ > 10   //  10 ---> n
    val p3: Int => Boolean = _ < -10  //   n <--- -10
    val p4: Int => Boolean = _ < 10   //   n <-0- 10
    val map1 = map(_ > 0, _ * 2)
  }

  /**
   * This test is currently disabled (by using "ignore") because the method
   * "singletonSet" is not yet implemented and the test would fail.
   *
   * Once you finish your implementation of "singletonSet", exchange the
   * function "ignore" by "test".
   */
  test("singletonSet(1) contains 1") {

    /**
     * We create a new instance of the "TestSets" trait, this gives us access
     * to the values "s1" to "s3".
     */
    new TestSets {
      /**
       * The string argument of "assert" is a message that is printed in case
       * the test fails. This helps identifying which assertion failed.
       */
      assert(contains(s1, 1), "Singleton")
    }
  }

  test("union contains all elements of each set") {
    new TestSets {
      val s = union(s1, s2)
      assert(contains(s, 1), "Union 1")
      assert(contains(s, 2), "Union 2")
      assert(!contains(s, 3), "Union 3")
    }
  }

  test("intersect contains elements that are presented in both sets") {
    new TestSets {
      assert(!contains(intersect1, 9), "intersect1 value 9")
      assert(contains(intersect1, 10), "intersect1 value 10")
      assert(!contains(intersect1, 11), "intersect1 value 11")
    }
  }

  test("diff contains elements of one set that are not in the other set") {
    new TestSets {
      assert(!contains(diff1, 9), "diff1 value 9")
      assert(!contains(diff1, 10), "diff1 value 10")
      assert(contains(diff1, 11), "diff1 value 11")
    }
  }

  test("filter contains elements of one set that also satisfy the predicate") {
    new TestSets {
      assert(!contains(filter1, 1), "filter1 value 1")
      assert(contains(filter1, 7), "filter1 value 7")
      assert(!contains(filter1, 15), "filter1 value 15")
    }
  }

  test("forall checks whether all bounded integers within the set satisfy the predicate") {
    new TestSets {
      assert(forall(s4, p1), "s4, p1")
      assert(!forall(s4, p2), "s4, p2")
      assert(!forall(s4, p3), "s4, p3")
      assert(!forall(s4, p4), "s4, p4")
      assert(forall(s7, p4), "s7, p4")
    }
  }

  test("exists checks whether there exists a bounded integer within the set that satisfies the predicate") {
    new TestSets {
      assert(exists(s4, p1), "s4, p1")
      assert(exists(s4, p2), "s4, p2")
      assert(!exists(s4, p3), "s4, p3")
      assert(exists(s4, p4), "s4, p4")
      assert(exists(s7, p4), "s7, p4")
    }
  }

//  test("map transforms the set by applying function to each element") {
//    new TestSets {
//      assert(exists(s4, p1), "s4, p1")
//      assert(exists(s4, p2), "s4, p2")
//      assert(!exists(s4, p3), "s4, p3")
//      assert(exists(s4, p4), "s4, p4")
//      assert(exists(s7, p4), "s7, p4")
//    }
//  }

}
