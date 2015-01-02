

abstract class runner {
  def testCase(): Boolean = {
    true
  }

  def mainCase(): Any

  def main(args: Array[String]) {
    assert(testCase())
    println(timer.time(mainCase()))
  }
}
