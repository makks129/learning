/** Functions and State **/

// Objects with state are represented by objects that have variable members

class BankAccount {
  private var balance = 0

  def deposit(amount: Int): Unit = {
    if (amount > 0) balance = balance + amount
  }

  def withdraw(amount: Int): Int = {
    if (amount > 0 && balance >= amount) {
      balance = balance - amount
      balance
    } else throw new Error("insufficient funds")
  }
}

val account = new BankAccount
account deposit 50
account withdraw 20 // same calls, different results
account withdraw 20
//account withdraw 20 // Error - insufficient funds

// Operational equivalence test
// Experiment 1:
val x1 = new BankAccount
val y1 = new BankAccount
x1 deposit 30
//y1 withdraw 20 // Error
val x2 = new BankAccount
val y2 = new BankAccount
x2 deposit 30
x2 withdraw 20 // No error
// Conclusion: x and y are not the same.

// Substitution model stops being valid as we add assignments!
/* Proof:
val x = new BankAccount
val y = x
Using substitution model this would be equal to:
val x = new BankAccount
val y = new BankAccount
And we just proved this they are not equal!
*/

/** Loops **/

// While and do-while loops translate to simple calls of tail-recursive HOFs
// For expressions (for with yield) translate to map and flatMap calls
// For loops (for without yield) translate to forEach calls
// For example:
for (i <- 1 until 3; j <- "abc") println(i + " " + j)
// translates to
(1 until 3) foreach (i => "abc" foreach (j => println(i + " " + j)))
