package p0to25

import common.runner


/**
By starting at the top of the triangle below and moving to adjacent numbers on the row below,
the maximum total from top to bottom is 23.

   3
  7 4
 2 4 6
8 5 9 3

That is, 3 + 7 + 4 + 9 = 23.

Find the maximum total from top to bottom of the triangle below:

              75
             95 64
            17 47 82
           18 35 87 10
          20 04 82 47 65
         19 01 23 75 03 34
        88 02 77 73 07 63 67
       99 65 04 28 06 16 70 92
      41 41 26 56 83 40 80 70 33
     41 48 72 33 47 32 37 16 94 29
    53 71 44 65 25 43 91 52 97 51 14
   70 11 33 28 77 73 17 78 39 68 17 57
  91 71 52 38 17 14 91 43 58 50 27 29 48
 63 66 04 68 89 53 67 30 73 16 69 87 40 31
04 62 98 27 23 09 70 98 73 93 38 53 60 04 23

NOTE: As there are only 16384 routes, it is possible to solve this problem by trying every
route. However, Problem 67, is the same challenge with a triangle containing one-hundred rows;
it cannot be solved by brute force, and requires a clever method! ;o)

  1074
 */
trait problem018 extends runner {
  val testInput = parse("""   3
                  7 4
                 2 4 6
                8 5 9 3""")
  val mainInput = parse(
    """ 75
    95 64
    17 47 82
    18 35 87 10
    20 04 82 47 65
    19 01 23 75 03 34
    88 02 77 73 07 63 67
    99 65 04 28 06 16 70 92
    41 41 26 56 83 40 80 70 33
    41 48 72 33 47 32 37 16 94 29
    53 71 44 65 25 43 91 52 97 51 14
    70 11 33 28 77 73 17 78 39 68 17 57
    91 71 52 38 17 14 91 43 58 50 27 29 48
    63 66 04 68 89 53 67 30 73 16 69 87 40 31
    04 62 98 27 23 09 70 98 73 93 38 53 60 04 23 """)

  type Triangle = Map[(Int, Int), Int]

  def parse(input: String): Triangle = {
    val d = for {
      (line, r) <- input.lines.zipWithIndex
      (digit, c) <- line.trim.split(" ").zipWithIndex
    } yield {
      (r, c) -> digit.toInt
    }
    d.toMap
  }

  def newValue(r: Int, c: Int, tri: Triangle): Int = {
    val value = tri((r, c))
    val belowLeft = tri((r + 1, c))
    val belowRight = tri((r + 1, c + 1))
    value + math.max(belowLeft, belowRight)
  }

  def solve(input: Triangle): Int = {
    val numRows = input.keys.max._1
    var result = input
    for {
      r <- numRows - 1 to 0 by -1
      c <- 0 to r
    } {
      result += ((r, c) -> newValue(r, c, result))
    }
    result(0, 0)
  }

  override def testCase(): Boolean = {
    solve(testInput) == 23
  }

  override def mainCase(): Long = {
    solve(mainInput)
  }
}

object problem018main extends problem018 {
  // just to run main
}

