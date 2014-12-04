import scala.collection.mutable
import scala.collection.mutable.ListBuffer

object primer extends runner {
  var primes = mutable.HashMap[Int, Boolean](2 -> true)

  def isPrime(num: Int): Boolean = {
    if (num < 2) {
      false
    } else {
      var i = 2
      val limit = math.sqrt(num.toDouble).toInt
      while (i <= limit) {
        if (num % i == 0)
        {
          return false
        }
        i += 1
      }
      true
    }
  }

  def isPrimeWithMap(num: Int): Boolean = {
    if (primes.contains(num)) {
      primes(num)
    } else {
      val p = isPrime(num)
      primes.put(num, p)
      p
    }
  }

  def genPrimes(limit: Int): List[Int] = {
    val toCheck = mutable.BitSet(3 to limit by 2: _*)
    val primes = ListBuffer(2)

    while (toCheck.nonEmpty) {
      val curr = toCheck.head
      toCheck.remove(curr)
      primes += curr

      var product = 0
      var i = 2
      while (product <= limit) {
        product = i * curr
        toCheck.remove(product)
        i += 1
      }
    }

    primes.toList
  }

  override def mainCase(): Long = {
//    (1 to 100).filter(isPrime).foreach(println)
//    timer.time(println(genPrimes(200000).size))
//    timer.time(println(genPrimes(200000).size))
//    timer.time(println(genPrimes(200000).size))
//    timer.time(println(genPrimes(200000).size))
    println(genPrimes(100).mkString(", "))
    0
  }
}
