package p26to50

import common.runner

/** https://projecteuler.net/problem=48
 * The series, 1^^1 + 2^^2 + 3^^3 + ... + 10^^10 = 10405071317.

Find the last ten digits of the series, 1^^1 + 2^^2 + 3^^3 + ... + 1000^^1000.

Answer:
9110846700
 */
object problem048 extends runner {
  val modder = BigInt(10000000000L)
  def calculate(limit: Int): BigInt = {
    (1 to limit).map(selfPower).sum.mod(modder)
  }

  def selfPower(num: Int): BigInt = {
    BigInt(num).modPow(num, modder)
  }

  override def mainCase(): Long = {
    println(calculate(1000))
    0
  }
}
