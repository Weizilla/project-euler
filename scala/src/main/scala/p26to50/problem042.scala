package p26to50

import common.runner

/** https://projecteuler.net/problem=42
  * The nth term of the sequence of triangle numbers is given by, tn = n(n+1)/2; so the first ten triangle numbers are:

1, 3, 6, 10, 15, 21, 28, 36, 45, 55, ...

By converting each letter in a word to a number corresponding to its alphabetical position and adding these values we form a word value. For example, the word value for SKY is 19 + 11 + 25 = 55 = t10. If the word value is a triangle number then we shall call the word a triangle word.

Using words.txt a 16K text file containing nearly two-thousand common English words, how many are triangle words?

  162
  */
object problem042 extends runner {
  def parse(input: String): List[String] = {
    input.split(",").map(_.replace("\"", "")).toList
  }

  def isTriangle(input: Int): Boolean = {
    val input2 = input * 2
    val sqrtLower = math.sqrt(input2).toInt
    sqrtLower * (sqrtLower + 1) == input2
  }

  def isTriangleWord(input: String): Boolean = {
    isTriangle(input.map(_ - 'A' + 1).sum)
  }

  override def mainCase(): Long = {
    val path = getClass.getResourceAsStream("problem042-words.txt")
    val input = parse(io.Source.fromInputStream(path).mkString)
    input.count(isTriangleWord)
    //input.foreach(println)
//    println(isTriangleWord("SKY"))
  }

}
