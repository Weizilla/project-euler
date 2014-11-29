/** https://projecteuler.net/problem=28
  * Starting with the number 1 and moving to the right in a clockwise direction a 5 by 5 spiral is formed as follows:

21 22 23 24 25
20  7  8  9 10
19  6  1  2 11
18  5  4  3 12
17 16 15 14 13

It can be verified that the sum of the numbers on the diagonals is 101.

What is the sum of the numbers on the diagonals in a 1001 by 1001 spiral formed in the same way?
  */
object problem028 extends runner{

  def calculate(limit: Int): Int = {
    def calculate(limit: Int, n: Int, num: Int, total: Int): Int = {
      val increase = (((n - 1) / 4) + 1) * 2
//      println(s"$n $num $increase $total")
      if (limit < increase) {
        total
      } else {
        val i: Int = num + increase
        calculate(limit, n + 1, i, total + i)
      }
    }
    calculate(limit, 1, 1, 1)
  }


  override def testCase(): Boolean = {
    calculate(5) == 101
  }

  override def mainCase(): Long = {
    calculate(1001)
  }
}
