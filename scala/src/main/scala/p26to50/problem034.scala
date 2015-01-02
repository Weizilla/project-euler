package p26to50

import common.runner

/** https://projecteuler.net/problem=34
 *
145 is a curious number, as 1! + 4! + 5! = 1 + 24 + 120 = 145.

Find the sum of all numbers which are equal to the sum of the factorial of their digits.

Note: as 1! = 1 and 2! = 2 are not sums they are not included.

40730
  */
object problem034 extends runner {
  var factorials = Map(0 -> 1, 1 -> 1, 2 -> 2, 3 -> 6, 4 -> 24, 5 -> 120, 6 -> 720,
    7 -> 5040, 8 -> 40320, 9 -> 362880
  )

  def calculateForNum(num: Int): Int = {
    num.toString.map(c => factorials(c.asDigit)).sum
  }

  override def mainCase(): Long = {
    val results = (3 to 100000).map(f => f -> calculateForNum(f)).filter { case (f, s) => f == s}
    println(results.mkString("\n"))
    results.map(_._1).sum
  }
}
