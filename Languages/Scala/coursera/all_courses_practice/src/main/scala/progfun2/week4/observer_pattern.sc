// Computerphile: Functional Programming & Haskell
// https://www.youtube.com/watch?v=LnX3B9oaKzw

/** Imperative Event Handling: The Observer Pattern **/

// Publisher / subscriber

trait Publisher {
  private var subscribers: Set[Subscriber] = Set()

  def subscribe(sub: Subscriber): Unit =
    subscribers += sub

  def unsubscribe(sub: Subscriber): Unit =
    subscribers -= sub

  def publish(): Unit =
    subscribers.foreach(_.handler(this))
}

trait Subscriber {
  def handler(pub: Publisher)
}

// Modified BankAccount form week3/week3
class BankAccount extends Publisher {
  private var balance = 0

  def currentBalance: Int = balance

  def deposit(amount: Int): Unit = {
    if (amount > 0) {
      balance = balance + amount
      publish()
    }
  }

  def withdraw(amount: Int): Unit = {
    if (amount > 0 && balance >= amount) {
      balance = balance - amount
      publish()
    } else throw new Error("insufficient funds")
  }
}

class Consolidator(observed: List[BankAccount]) extends Subscriber {
  observed.foreach(_.subscribe(this))

  private var total: Int = _
  compute()

  private def compute() =
    total = observed.map(_.currentBalance).sum

  override def handler(pub: Publisher): Unit = compute()

  def totalBalance: Int = total
}

val a = new BankAccount
val b = new BankAccount
val c = new Consolidator(List(a, b))
c.totalBalance
a deposit 20
c.totalBalance
b deposit 30
c.totalBalance

// Observer pattern conclusion:
//
// +
// Decouples views from state
// Varying number of views of a given state
// Simple to set up
//
// -
// Has to be imperative
// Concurrency makes things more complicated
// Views are tightly bound to state - view update happens immediately






































