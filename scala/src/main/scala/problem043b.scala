/** same as problem 43 but hopefully more efficient
  *
  * The number, 1406357289, is a 0 to 9 pandigital number because it is made up of each of the digits 0 to 9 in some order, but it also has a rather interesting sub-string divisibility property.

Let d1 be the 1st digit, d2 be the 2nd digit, and so on. In this way, we note the following:

d2d3d4=406 is divisible by 2
d3d4d5=063 is divisible by 3
d4d5d6=635 is divisible by 5
d5d6d7=357 is divisible by 7
d6d7d8=572 is divisible by 11
d7d8d9=728 is divisible by 13
d8d9d10=289 is divisible by 17
Find the sum of all 0 to 9 pandigital numbers with this property.

  16695334890
  */
object problem043b extends runner {

  def isDivisible(numList: String, divisor: Int): Boolean = {
    val num = numList.substring(0, 3).toLong
    num % divisor == 0 && numList.toCharArray.toSet.size == numList.size
  }

  def prefixDigits(numList: String): List[String] = {
    (0 to 9).toList.map(d => d + numList)
  }

  def calc(): Long = {
    var numbers = (0 to 99).map(i => f"$i%02d")
    val divisors = List(17, 13, 11, 7, 5, 3, 2, 1)
    for (d <- divisors) {
      numbers = numbers.flatMap(prefixDigits).filter(isDivisible(_, d))
    }
    numbers.map(_.toLong).sum
  }

  override def mainCase(): Any = {
    val result = calc()
    assert(result == 16695334890L)
    result
  }

}
