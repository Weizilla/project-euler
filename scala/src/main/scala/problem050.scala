/** https://projecteuler.net/problem=50
  * The prime 41, can be written as the sum of six consecutive primes:

41 = 2 + 3 + 5 + 7 + 11 + 13
This is the longest sum of consecutive primes that adds to a prime below one-hundred.

The longest sum of consecutive primes below one-thousand that adds to a prime, contains 21 terms, and is equal to 953.

Which prime, below one-million, can be written as the sum of the most consecutive primes?
 *
 */
object problem050 extends runner {
  override def mainCase(): Long = {
    val primes = primer.genPrimes(10000)
    var sum = 0
    val ps = for (p <- primes if sum < 100000) yield {
      println(sum + " " + primer.isPrime(sum))
      sum += p
      p
    }
    println(ps)
    0
  }
}
