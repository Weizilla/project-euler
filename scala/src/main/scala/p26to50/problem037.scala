package p26to50

import common.{primer, runner}

import scala.collection.mutable.ListBuffer

/** https://projecteuler.net/problem=37

The number 3797 has an interesting property. Being prime itself, it is possible to continuously remove digits from left to right, and remain prime at each stage: 3797, 797, 97, and 7. Similarly we can work from right to left: 3797, 379, 37, and 3.

Find the sum of the only eleven primes that are both truncatable from left to right and right to left.

NOTE: 2, 3, 5, and 7 are not considered to be truncatable primes.

  748317
*/
object problem037 extends runner {

  def bothPrime(strs: (String, String)): Boolean = {
    val (a, b) = strs
    primer.isPrime(a.toInt) && primer.isPrime(b.toInt)
  }

  def isTruncatePrime(num: Int): Boolean = {
    val str = num.toString
    (1 to str.length - 1).forall(i => bothPrime(str.splitAt(i)))
  }

  override def mainCase(): Long = {
    var found = ListBuffer.empty[Int]
    var i = 11
    while (found.size != 11) {
      if (primer.isPrime(i) && isTruncatePrime(i)) {
        found += i
      }

      i += 2
    }
    found.foreach(println)
    found.sum
  }
}

