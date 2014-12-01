import scala.collection.mutable

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

  override def mainCase(): Long = {
//    timer.time((1 to 5000000).foreach(isPrime), message = "0")
//    timer.time((1 to 5000000).foreach(isPrimeWithMap), message = "2")
//    timer.time((1 to 5000000).foreach(isPrime), message = "0")
//    timer.time((1 to 5000000).foreach(isPrimeWithMap), message = "2")
//    timer.time((1 to 5000000).foreach(isPrime), message = "0")
//    timer.time((1 to 5000000).foreach(isPrimeWithMap), message = "2")
    (1 to 100).filter(isPrime).foreach(println)
    0
  }
}
