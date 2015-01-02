package p26to50

import common.runner

/**
 * https://projecteuler.net/problem=32
 * We shall say that an n-digit number is pandigital if it makes use of all the digits 1 to n exactly once; for example, the 5-digit number, 15234, is 1 through 5 pandigital.

The product 7254 is unusual, as the identity, 39 × 186 = 7254, containing multiplicand, multiplier, and product is 1 through 9 pandigital.

Find the sum of all products whose multiplicand/multiplier/product identity can be written as a 1 through 9 pandigital.

HINT: Some products can be obtained in more than one way so be sure to only include it once in your sum.

Answer:
45228
 */
object problem032 extends runner {
  def validProducts(num: String): Set[Int] = {
    var valid = Set.empty[(Int, Int, Int)]
    for {
      p <- 1 to 8
      e <- p to 8
      if p < e
    } {
      val a = num.take(p).toInt
      val b = num.slice(p, e).toInt
      val c = num.drop(e).toInt
      if (a < c && b < c && a * b == c) {
        valid += ((a, b, c))
      }
    }
    valid.map(_._3)
  }

  override def mainCase(): Long = {
    (1 to 9).permutations.toList.map(_.mkString).flatMap(validProducts).toSet.sum
  }
}
