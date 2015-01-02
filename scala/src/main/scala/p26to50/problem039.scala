package p26to50

import common.runner

/** https://projecteuler.net/problem=39
If p is the perimeter of a right angle triangle with integral length sides,
{a,b,c}, there are exactly three solutions for p = 120.

{20,48,52}, {24,45,51}, {30,40,50}

For which value of p ≤ 1000, is the number of solutions maximised?
  840
  */
object problem039 extends runner {
  def calcC(a: Int, b: Int): Option[Int] = {
    val c2 = math.pow(a, 2) + math.pow(b, 2)
    val c = math.sqrt(c2).toInt
    if (c * c == c2) Some(c) else None
  }
  override def mainCase(): Long = {
    val allValues = for {
      a <- 1 to 998
      b <- 1 to 998
      c <- calcC(a, b)
      if a < b && b < c && a + b + c < 1000
    } yield {
      (a, b, c, a + b + c)
    }

    allValues.groupBy(v => v._4).maxBy(_._2.size)._1
  }
}
