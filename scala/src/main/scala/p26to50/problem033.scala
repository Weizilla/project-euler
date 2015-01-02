package p26to50

import common.{factor, runner}

/** https://projecteuler.net/problem=33
  * The fraction 49/98 is a curious fraction, as an inexperienced mathematician in attempting to simplify it may incorrectly believe that 49/98 = 4/8, which is correct, is obtained by cancelling the 9s.

We shall consider fractions like, 30/50 = 3/5, to be trivial examples.

There are exactly four non-trivial examples of this type of fraction, less than one in value, and containing two digits in the numerator and denominator.

If the product of these four fractions is given in its lowest common terms, find the value of the denominator.
100
  */
object problem033 extends runner {
  def findFractions(): List[(Int, Int)] = {
    var valid = List.empty[(Int, Int)]
    for {
      n <- 11 to 99
      d <- n to 99
      if n < d && n % 10 != 0 && d % 10 != 0 }
    {
      val nTens = n / 10
      val nDigit = n % 10
      val dTens = d / 10
      val dDigit = d % 10
      if (nDigit == dTens && nTens.toDouble / dDigit.toDouble == n.toDouble / d.toDouble) {
        valid = (n, d) +: valid
      }
    }
    valid
  }

  def multiplyAndReduce(fractions: List[(Int, Int)]): (Int, Int) = {
    val result = fractions.reduce((a, b) => (a._1 * b._1, a._2 * b._2))
    println(result)
    val g = gcd(result._1, result._2)
    val r2 = (result._1 / g, result._2 / g)
    r2
  }

  def gcd(a: Int, b: Int): Int = {
    val fa = factor.factor(a).toSet
    val fb = factor.factor(b).toSet
    val common = fa & fb
    common.max
  }

  override def mainCase(): Long = {
    val fractions = findFractions()
    fractions.foreach(println(_))
    println(fractions)
    val r = multiplyAndReduce(fractions)
    println(r)
    r._2
  }

}
