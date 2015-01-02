package p26to50

import common.{primer, runner}

/** https://projecteuler.net/problem=41
  * We shall say that an n-digit number is pandigital if it makes use of all the digits 1 to n exactly once. For example, 2143 is a 4-digit pandigital and is also prime.

What is the largest n-digit pandigital prime that exists?
7652413
  */
object problem041 extends runner {
  override def mainCase(): Long = {
    val combos = for (i <- 2 to 9) yield (1 to i).permutations
    combos.flatten.map(_.mkString.toInt).sorted.reverse.find(primer.isPrime).get
  }
}
