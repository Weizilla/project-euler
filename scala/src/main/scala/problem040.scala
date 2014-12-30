/** https://projecteuler.net/problem=40
  * An irrational decimal fraction is created by concatenating the positive integers:

0.123456789101112131415161718192021...

It can be seen that the 12th digit of the fractional part is 1.

If dn represents the nth digit of the fractional part, find the value of the following expression.

d1 × d10 × d100 × d1000 × d10000 × d100000 × d1000000

210
  */
object problem040 extends runner {
  override def mainCase(): Long = {
    val str = (0 to 1000000).mkString
    val i = List(1, 10, 100, 1000, 10000, 100000, 1000000)
    i.map(str.charAt(_).asDigit).product
  }
}
