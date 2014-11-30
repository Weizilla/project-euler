

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

object problem027 extends runner {
  var primes = mutable.HashMap.empty[Int, Boolean]

  def isPrime(num: Int): Boolean = {
    if (num < 2) {
      false
    } else if (primes.contains(num)) {
      primes(num)
    } else {
      val prime = (2 to (math.sqrt(num.toDouble).toInt + 1)).forall(num % _ != 0)
      primes.put(num, prime)
      prime
    }
  }


  override def mainCase(): Long = {
//    val results = ListBuffer.empty[(Int, Int, Int, Int)]
    val results = ArrayBuffer.empty[(Int, Int, Int, Int)]
    for {
      a <- -999 to 999
      b <- 2 to 999
      if isPrime(b) && isPrime(1 + a + b)
    } {
      var r = 1
      var n = 0
      do {
        r = n * n + n * a + b
        n += 1
      } while (isPrime(r))
      val result = (a, b, r, n - 1)
      results += result
    }

    val max = results.maxBy(_._4)
    println(results.filter(i => i._1 == -999 && i._2 == 61))
    val i: Int = max._1 * max._2
    println(max.toString() + " " + i)
    i
  }
}
