package p0to25

import common.runner


/** https://projecteuler.net/problem=23
A perfect number is a number for which the sum of its proper divisors is exactly equal
to the number. For example, the sum of the proper divisors of 28 would be
1 + 2 + 4 + 7 + 14 = 28, which means that 28 is a perfect number.

A number n is called deficient if the sum of its proper divisors is less than n and it is
called abundant if this sum exceeds n.

As 12 is the smallest abundant number, 1 + 2 + 3 + 4 + 6 = 16, the smallest number that can
be written as the sum of two abundant numbers is 24. By mathematical analysis, it can be
shown that all integers greater than 28123 can be written as the sum of two abundant numbers.
However, this upper limit cannot be reduced any further by analysis even though it is known
that the greatest number that cannot be expressed as the sum of two abundant numbers is less
than this limit.

Find the sum of all the positive integers which cannot be written as the sum of two abundant
numbers.

  4179871
  */
object problem023 extends runner {
  def isAbundant(num: Int): Boolean = {
    val f = factors(num)
    val a = f.sum > num
//    println(s"$num $f $a")
    a
  }

  def factors(num: Int): Set[Int] = {
    val limit = (math.sqrt(num) + 1).toInt
    val factors = List.range(1, limit).toSet
    val f = factors.filter(num % _ == 0)
    val f2 = f.map(num / _)
    f ++ f2 - num
  }

  override def mainCase(): Long = {
    var nums = (0 until 28123).toSet
    val abundant = nums.filter(isAbundant)

    for {
      i <- abundant
      j <- abundant
    } {
      nums -= (i + j)
    }

    nums.sum
  }
}
