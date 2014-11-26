import scala.collection.mutable

/**
https://projecteuler.net/problem=15
Starting in the top left corner of a 2×2 grid, and only
being able to move to the right and down, there are exactly 6 routes to the bottom right corner.

How many such routes are there through a 20×20 grid?
  137846528820
 */
object problem015 extends runner {
  // this is idential to the problem where you build a triangle of numbers where each
  // number is the sum of the two above it. Each number in the triangle represents a
  // vertix in the square w/ the value the number of paths to that point
  //
  // 0 - 1  ==>   0
  // |   |       1 1
  // 1 - 2      1 2 1

  val values = mutable.Map[(Int, Int), BigInt]()

  def calc(r: Int, c: Int): BigInt = {
    if (r == 0 || c == 0 || c == r) BigInt(1)
    else if (values.contains((r, c))) values((r, c))
    else {
      val result = calc(r - 1, c) + calc(r - 1, c - 1)
      values.put((r, c), result)
      result
    }
  }

  override def mainCase(): Long = {
    val size = 20
    val r = size * 2
    calc(r, size).toLong
  }
}

