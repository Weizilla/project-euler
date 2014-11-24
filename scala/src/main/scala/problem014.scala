import scala.collection.mutable

/**
The following iterative sequence is defined for the set of positive integers:

n → n/2 (n is even)
n → 3n + 1 (n is odd)

Using the rule above and starting with 13, we generate the following sequence:

13 → 40 → 20 → 10 → 5 → 16 → 8 → 4 → 2 → 1
It can be seen that this sequence (starting at 13 and finishing at 1) contains 10 terms. Although it has not been proved yet (Collatz Problem), it is thought that all starting numbers finish at 1.

Which starting number, under one million, produces the longest chain?

NOTE: Once the chain starts the terms are allowed to go above one million.

837799
 */

object problem014 extends runner {
  var allMaps = mutable.Map[BigInt,Int](BigInt(1) -> 1)

  def collatz(num: BigInt): Int = {
    def collatz(num: BigInt, acc: Int, steps: List[(BigInt, Int)]): Int = {
      num match {
        case n if allMaps.contains(n) =>
          val accForN = allMaps(n)
          for ((k, v) <- steps) allMaps.put(k, acc - v + accForN)
          accForN + acc - 1
        case n if n.mod(BigInt(2)).equals(BigInt(0)) =>
          collatz(n / 2, acc + 1, (num -> acc) :: steps)
        case n if n.mod(BigInt(2)).equals(BigInt(1)) =>
          collatz(3 * n + 1, acc + 1, (num -> acc) :: steps)
      }
    }
    collatz(num, 1, List())
  }

  override def run(): Any = {
    List.range(1, 1000000).map(collatz(_)).zipWithIndex.maxBy(_._1)._2 + 1
  }
}
