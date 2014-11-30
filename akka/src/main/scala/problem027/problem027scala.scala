package problem027

import common.Timer

import scala.collection.mutable.ArrayBuffer

object problem027scala extends Timer {

  def main(args: Array[String]) {
    def calculate {
      val primes = problem027akka.calculatePrimes(1997002)
      println("Found " + primes.size + " primes")

      val results = ArrayBuffer.empty[(Int, Int, Int, Int)]
//      var results = List.empty[(Int, Int, Int, Int)]

      for {
        a <- -999 to 999
        b <- 2 to 999
        if primes(b) && primes(1 + a + b)
      } {
        var r = 1
        var n = 0
        do {
          r = n * n + n * a + b
          n += 1
        } while (primes(r))
        val result = (a, b, r, n - 1)
        results += result
      }

      val max = results.maxBy(_._4)
      println("FINAL: " + max.toString() + " " + max._1 * max._2)
    }
    time(calculate)
  }

}
