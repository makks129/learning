/** HOF and Currying **/

def sum_R(f: Int => Int)(a: Int, b: Int): Int = {
  if (a > b) 0 else f(a) + sum_R(f)(a + 1, b)
}

def sum_TR(f: Int => Int)(a: Int, b: Int): Int = {
  def loop(res: Int, a: Int): Int =
    if (a > b) res
    else loop(f(a) + res, a + 1)
  loop(0, a)
}

def sumF(f: Int => Int): (Int, Int) => Int = {
  def loop(a: Int, b: Int): Int =
    if (a > b) 0 else f(a) + loop(a + 1, b)
  loop
}

def sumFCurried(f: Int => Int)(a: Int, b: Int): Int = {
  if (a > b) 0 else f(a) + sumFCurried(f)(a + 1, b)
}

sumF(x => x * 2)(1, 5)
sumFCurried(x => x * 2)(1, 5)


def product_R(f: Int => Int)(a: Int, b: Int): Int = {
  if (a > b) 1 else f(a) * product_R(f)(a + 1, b)
}

val fact = product_R(x => x) _

// my version of generalized solution
def interval(unit: Int, comb: (Int, Int) => Int)
            (f: Int => Int)
            (a: Int, b: Int): Int = {
  if (a > b) unit else comb(f(a), interval(unit, comb)(f)(a + 1, b))
}

val fact2 = interval(1, _ * _)(x => x) _

// not my version of generalized solution
def mapReduce(f: Int => Int, comb: (Int, Int) => Int, zero: Int)(a: Int, b: Int): Int = {
  if (a > b) zero else comb(f(a), mapReduce(f, comb, zero)(a + 1, b))
}


/** Functions and Data **/

class Rational(x: Int, y: Int) {
  require(y != 0, "denominator must be nonzero")

  private def gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)

  private val g = gcd(x, y)

  def this(x: Int) = this(x, 1) // additional constructor

  def numer = x / g

  def denom = y / g

  // method
  def +(that: Rational) = new Rational(numer * that.denom + that.numer * denom, denom * that.denom)

  def unary_- = new Rational(-numer, denom)

  def -(that: Rational) = this + -that

  def <(that: Rational) = this.numer * that.denom < that.numer * this.denom

  def max(that: Rational) = if (this < that) that else this

  override def toString: String = numer + "/" + denom
}

// function
def addRational(r: Rational, s: Rational) =
new Rational(r.numer * s.denom + s.numer * r.denom, r.denom * s.denom)

def makeString(r: Rational) = r.numer + "/" + r.denom

makeString(addRational(new Rational(1, 2), new Rational(2, 3)))

val x = new Rational(1, 3)
val y = new Rational(5, 7)
val z = new Rational(3, 2)
x - y - z


//val strange = new Rational(1, 0) // IllegalArgumentException

new Rational(2) // uses additional constructor
