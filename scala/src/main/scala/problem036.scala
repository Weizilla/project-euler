/** https://projecteuler.net/problem=36
 *
The decimal number, 585 = 10010010012 (binary), is palindromic in both bases.

Find the sum of all numbers, less than one million, which are palindromic in base 10 and base 2.

(Please note that the palindromic number, in either base, may not include leading zeros.)

  872187
 */
object problem036 extends runner {
  def isDbPalindrome(num: Int): Boolean = {
    val nStr = num.toString
    val bStr = num.toBinaryString
    nStr == nStr.reverse && bStr == bStr.reverse
  }
  override def mainCase(): Long = {
    (0 to 1000000).filter(isDbPalindrome).sum
  }
}
