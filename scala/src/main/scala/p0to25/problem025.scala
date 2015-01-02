package p0to25

import common.runner

/** https://projecteuler.net/problem=25
  * The Fibonacci sequence is defined by the recurrence relation:

Fn = Fn−1 + Fn−2, where F1 = 1 and F2 = 1.
Hence the first 12 terms will be:

F1 = 1
F2 = 1
F3 = 2
F4 = 3
F5 = 5
F6 = 8
F7 = 13
F8 = 21
F9 = 34
F10 = 55
F11 = 89
F12 = 144
The 12th term, F12, is the first term to contain three digits.

What is the first term in the Fibonacci sequence to contain 1000 digits?
  4782
  */
object problem025 extends runner {
  def fib(limit: (Int, BigInt) => Boolean): (Int, BigInt) = {
    def fib(limit: (Int, BigInt) => Boolean, n: Int, last: BigInt, last2: BigInt): (Int, BigInt) = {
      val curr = last + last2
      if (limit(n, curr)) {
        (n, curr)
      } else {
        fib(limit, n + 1, curr, last)
      }
    }
    fib(limit, 3, 1, 1)
  }

  def calc(limit: (Int, BigInt) => Boolean): Int = {
    val (n, acc) = fib(limit)
    println(n + " " + acc)
    n
  }

  override def testCase(): Boolean = calc((n, acc) => acc.toString().size == 3) == 12

  override def mainCase(): Long = {
    calc((n, acc) => acc.toString().size == 1000)
  }
}
