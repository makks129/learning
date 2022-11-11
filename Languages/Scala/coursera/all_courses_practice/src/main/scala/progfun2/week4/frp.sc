
/** Simple FRP Implementation **/

class Signal[T](expr: => T) {
  import Signal._

  private var myExpr: () => T = _
  private var myValue: T = _
  private var observers: Set[Signal[_]] = Set()
  update(expr)


  protected def update(expr: => T): Unit = {
    myExpr = () => expr
    computeValue()
  }

  protected def computeValue(): Unit = {
    val newValue = caller.withValue(this)(myExpr())
    if (myValue != newValue) {
      myValue = newValue
      val obs = observers
      observers = Set()
      obs.foreach(_.computeValue())
    }
  }

  def apply(): T = {
    observers += caller.value
    assert(!caller.value.observers.contains(this), "cyclic signal definition")
    myValue
  }
}

object NoSignal extends Signal[Nothing](???) {
  // ??? here means expr is and will not be implemented
  override def computeValue(): Unit = ()
}

object Signal {
  // Problem with this implementation of caller is that it's not thread-safe:
  // multiple threads can use this thread-global variable
//  private val caller = new StackableVariable[Signal[_]](NoSignal)
  // Simple solution is to use thread-local variable scala.util.DynamicVariable
  // It's API is exactly the same as StackableVariable, so we can just swap them
  import scala.util.DynamicVariable
  private val caller = new DynamicVariable[Signal[_]](NoSignal)


  def apply[T](expr: => T): Signal[T] = new Signal(expr)
}


class Var[T](expr: => T) extends Signal[T](expr) {
  override def update(expr: => T): Unit =
  // by such implementation we expose update(expr) method in Signal publicly, if used though Var
    super.update(expr)

}

object Var {
  def apply[T](expr: => T): Var[T] = new Var(expr)
}

class StackableVariable[T](init: T) {
  private var values: List[T] = List(init)

  def value: T = values.head

  def withValue[R](newValue: T)(op: => R): R = {
    values = newValue :: values
    try op finally values = values.tail
  }
}

// Implementation conclusion (4.3, 17:20)
//
// This implementation has several disadvantages
// 1. Imperative nature
// 2. Involves global hash table lookup
// 3. Doesn't play well in situation where threads are multiplexed between several tasks
//
// Cleaner solution involves implicit parameters:
// 1. Purely functional (though more boilerplate)
// 2. No thread-local variable, just pass it's current value into signal as an implicit parameter


/** Functional Reactive Programming **/

// Modified BankAccount form week3/week3
class BankAccount {
  val balance = Var(0)

  def deposit(amount: Int): Unit = {
    if (amount > 0) {
      val b = balance()
      balance() = b + amount
    }
  }

  def withdraw(amount: Int): Unit = {
    if (amount > 0 && balance() >= amount) {
      val b = balance()
      balance() = b - amount
    } else throw new Error("insufficient funds")
  }
}