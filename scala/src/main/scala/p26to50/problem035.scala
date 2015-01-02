package p26to50

import common.{primer, runner}

/** https://projecteuler.net/problem=35
 * The number, 197, is called a circular prime because all rotations of the digits: 197, 971, and 719, are themselves prime.

There are thirteen such primes below 100: 2, 3, 5, 7, 11, 13, 17, 31, 37, 71, 73, 79, and 97.

How many circular primes are there below one million?
55
 */
object problem035 extends runner {
  def calculate(limit: Int): Int = {
    (1 to limit by 2).count(circularPrime) + 1 // for 2
  }
  def circularPrime(num: Int): Boolean = {
    val str = num.toString
    val rotations = for {
      i <- 0 to str.size
    } yield {
      (str.drop(i) + str.take(i)).toInt
    }
    rotations.forall(primer.isPrime)
  }

//  override def testCase(): Boolean = {
//    calculate(100) == 13
//  }

  override def mainCase(): Long = {
    calculate(1000000)
  }
}
