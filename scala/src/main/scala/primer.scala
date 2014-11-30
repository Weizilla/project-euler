import scala.collection.mutable

object primer {
  var primes = mutable.HashMap[Int, Boolean](2 -> true)

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

}
