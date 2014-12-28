

/**https://projecteuler.net/problem=38
  *
  * Take the number 192 and multiply it by each of 1, 2, and 3:

192 × 1 = 192
192 × 2 = 384
192 × 3 = 576
By concatenating each product we get the 1 to 9 pandigital, 192384576. We will call 192384576
the concatenated product of 192 and (1,2,3)

The same can be achieved by starting with 9 and multiplying by 1, 2, 3, 4, and 5, giving the
pandigital, 918273645, which is the concatenated product of 9 and (1,2,3,4,5).

What is the largest 1 to 9 pandigital 9-digit number that can be formed as the concatenated
product of an integer with (1,2, ... , n) where n > 1?

  932718654

  */
object problem038 extends runner {
  def isValid(num: Int): (Boolean, List[Char]) = {
    var found = Set.empty[Char]
    var allDigits = List.empty[Char]
    for (i <- 1 to 2) {
      val numChars = (i * num).toString.toCharArray
      found = found ++ numChars
      if (found('0')) {
        return (false, allDigits)
      }
      allDigits =  allDigits ++ numChars
      if (found.size != allDigits.size) {
        return (false, allDigits)
      } else if (found.size == 9) {
        return (true, allDigits)
      }
    }
    (found.size == allDigits.size && found.size == 9, allDigits)
  }

  override def mainCase(): Long = {
    (1 to 10000).map(isValid(_)).filter(_._1).map(_._2.mkString.toLong).max
  }
}
