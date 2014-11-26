/**
If the numbers 1 to 5 are written out in words: one, two, three, four, five, then
there are 3 + 3 + 5 + 4 + 4 = 19 letters used in total.

If all the numbers from 1 to 1000 (one thousand) inclusive were written out in words,
how many letters would be used?


NOTE: Do not count spaces or hyphens. For example, 342 (three hundred and forty-two)
contains 23 letters and 115 (one hundred and fifteen) contains 20 letters.
The use of "and" when writing out numbers is in compliance with British usage.

21124
 */
object problem017 extends runner {
  val words = Map(1 -> "one", 2 -> "two", 3 -> "three", 4 -> "four", 5 -> "five",
  6 -> "six", 7 -> "seven", 8 -> "eight", 9 -> "nine", 10 -> "ten", 11 -> "eleven",
  12 -> "twelve", 13 -> "thirteen", 14 -> "fourteen", 15 -> "fifteen", 16 -> "sixteen",
  17 -> "seventeen", 18 -> "eighteen", 19 -> "nineteen", 20 -> "twenty", 30 -> "thirty",
  40 -> "forty", 50 -> "fifty", 60 -> "sixty", 70 -> "seventy", 80 -> "eighty",
  90 -> "ninety", 1000 -> "onethousand")

  def toWords(num: Int): String = {
    if (words.contains(num)) {
      words(num)
    } else if (num < 100) {
      val ones = num % 10
      val tens = num - ones
      words(tens) + words(ones)
    } else if (num < 1000) {
      val tensandones = num % 100
      val hundreds = num / 100
      var result = toWords(hundreds) + "hundred"
      if (tensandones != 0)
      {
        result += "and" + toWords(tensandones)
      }
      result
    } else {
      throw new IllegalArgumentException("Unsupported number: " + num)
    }
  }

  def count(num: Int): Int = {
    try {
      val str = toWords(num)
//      println("STR: " + str + " size: " + str.size)
      str.size
    } catch {
      case e =>
        println("Error for num " + num)
        e.printStackTrace()
        throw e
    }
  }

  override def testCase(): Boolean = {
    (1 to 5).map(count).sum == 19 &&
      count(342) == 23 &&
      count(115) == 20
  }

  override def mainCase(): Long = {
    (1 to 1000).map(count).sum
  }
}
