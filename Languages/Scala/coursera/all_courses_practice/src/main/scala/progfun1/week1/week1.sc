import scala.annotation.tailrec

def abs(x: Double) = if (x < 0) -x else x

def sqrtIter(guess: Double, x: Double): Double =
  if (isGoodEnough(guess, x)) guess
  else sqrtIter(improve(guess, x), x)

def isGoodEnough(guess: Double, x: Double) =
  abs(guess * guess - x) < 0.001

def improve(guess: Double, x: Double) = (guess + x / guess) / 2

def sqrt(x: Double) = sqrtIter(1.0, x)

sqrt(2)
sqrt(4)
sqrt(1e-6)
//sqrt(1e60) // hangs

def sqrtIter2(guess: Double, x: Double): Double =
  if (isGoodEnough2(guess, x)) guess
  else sqrtIter2(improve2(guess, x), x)

def isGoodEnough2(guess: Double, x: Double) =
//  abs(1 - (x / (guess * guess))) < 0.0001 // my
  abs(guess * guess - x) / x < 0.001

def improve2(guess: Double, x: Double) = (guess + x / guess) / 2

def sqrt2(x: Double) = sqrtIter2(1.0, x)

sqrt2(2)
sqrt2(4)
sqrt2(1e-6)
sqrt2(1e60)

def sqrt3(x: Double) = {
  def sqrtIter(guess: Double): Double =
    if (isGoodEnough(guess)) guess
    else sqrtIter(improve(guess))
  def isGoodEnough(guess: Double) =
    abs(guess * guess - x) / x < 0.001
  def improve(guess: Double) =
    (guess + x / guess) / 2

  sqrtIter(1.0)
}


def factorial_R(n: Int): Int =
  if (n == 0) 1 else n * factorial_R(n - 1)

def factorial_TR(n: Int) = {
  @tailrec def factorial_TR_acc(n: Int, res: Int): Int =
    if (n == 0) res else factorial_TR_acc(n - 1, res * n)
  factorial_TR_acc(n, 1)
}

factorial_R(4)
factorial_TR(4)