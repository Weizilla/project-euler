package p0to25

import common.runner

/**
https://projecteuler.net/problem=16
  2**15 = 32768 and the sum of its digits is 3 + 2 + 7 + 6 + 8 = 26.

What is the sum of the digits of the number 2**1000?
1366
 */

object problem016 extends runner {

  def calculate(input: Int): Int = {
    BigInt(2).pow(input).toString().map(_.asDigit).sum
  }

  override def testCase() = {
    calculate(15) == 26
  }

  override def mainCase(): Long = {
    calculate(1000)
  }
}

