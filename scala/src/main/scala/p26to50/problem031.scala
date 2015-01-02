package p26to50

import common.runner

import scala.collection.immutable.List

/** https://projecteuler.net/problem=31
  * In England the currency is made up of pound, £, and pence, p, and there are eight coins in general circulation:

1p, 2p, 5p, 10p, 20p, 50p, £1 (100p) and £2 (200p).
It is possible to make £2 in the following way:

1×£1 + 1×50p + 2×20p + 1×5p + 1×2p + 3×1p
How many different ways can £2 be made using any number of coins?

73682
  */
object problem031 extends runner {
  def calculate(pieces: List[Int], limit: Int): List[List[Int]] = {

    def calculate(pieces: List[Int], limit: Int, sum: Int, stack: List[Int]): List[List[Int]] = {
      if (sum == limit) {
        List(stack)
      } else if (sum > limit || pieces.isEmpty) {
        List()
      } else {
        val head = pieces.head
        calculate(pieces, limit, sum + head, head +: stack) ++ calculate(pieces.tail, limit, sum, stack)
      }
    }

    calculate(pieces, limit, 0, List()).filter(_.nonEmpty)
  }


  override def mainCase(): Long = {
    val stacks = calculate(List(1, 2, 5, 10, 20, 50, 100, 200).sorted.reverse, 200)
//    println(stacks.mkString("\n"))
    stacks.size
  }
}
