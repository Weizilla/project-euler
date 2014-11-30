

/** https://projecteuler.net/problem=26
 * A unit fraction contains 1 in the numerator. The decimal representation of the unit fractions with denominators 2 to 10 are given:

1/2  =   0.5
1/3  =   0.(3)
1/4  =   0.25
1/5  =   0.2
1/6  =   0.1(6)
1/7  =   0.(142857)
1/8  =   0.125
1/9  =   0.(1)
1/10  =   0.1
Where 0.1(6) means 0.166666..., and has a 1-digit recurring cycle. It can be seen that 1/7 has a 6-digit recurring cycle.

Find the value of d < 1000 for which 1/d contains the longest recurring cycle in its decimal fraction part.
  983
 */
object problem026 extends runner {

  def calculateNumRepeating(denom: Int): (Int, Int) = {
    var divisor = 1
    var counter = 1
    var remainders = List.empty[Int]

    for (i <- 1 to denom) {
//      divisor *= 10
      divisor = divisor * 10 % denom
      val i = remainders.indexOf(divisor)
      if (i != -1) {
        return (denom, remainders.size - i)
      } else {
        remainders = remainders :+ divisor
      }
      if (divisor == 0) {
        return (denom, counter)
      } else {
        counter += 1
      }
    }
    (-1, -1)
  }

  override def mainCase(): Long = {
    (1 to 999).map(calculateNumRepeating(_)).maxBy(_._2)._1
  }
}
