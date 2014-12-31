/** same as problem 43 but hopefully more efficient
  16695334890
  */
object problem043b extends runner {
  def isValid(num: String): Boolean = {
    val divisor = Array(2, 3, 5, 7, 11, 13, 17)
    (1 to 7).forall(i => num.substring(i, i + 3).toInt % divisor(i - 1) == 0)
  }

  def calc(): Long = {
    (0 to 9).permutations.map(i => i.mkString).filter(_.size == 10).filter(isValid).map(_.toLong).sum
  }

  override def mainCase(): Long = {
    val result = calc()
    assert(result == 16695334890L)
    result
  }

}
