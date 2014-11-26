/** https://projecteuler.net/problem=20
  * n! means n × (n − 1) × ... × 3 × 2 × 1

For example, 10! = 10 × 9 × ... × 3 × 2 × 1 = 3628800,
and the sum of the digits in the number 10! is 3 + 6 + 2 + 8 + 8 + 0 + 0 = 27.

Find the sum of the digits in the number 100!
  648
  */
object problem020 extends runner {
  def calc(limit: Int): Int = {
    fact(limit).toString().map(_.asDigit).sum
  }

  def fact(limit: Int): BigInt = {
    def fact(num: Int, acc: BigInt): BigInt = {
      if (num == 1) {
        acc
      } else {
        fact(num - 1, acc * num)
      }
    }
    fact(limit, 1)
  }

  override def testCase(): Boolean = {
    calc(10) == 27
  }

  override def mainCase(): Long = {
    calc(100)
  }
}
