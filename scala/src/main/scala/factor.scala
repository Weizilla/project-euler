import scala.collection.mutable
import scala.collection.mutable.ListBuffer


object factor {
  var factorMap = mutable.HashMap.empty[Int, List[Int]]

  def factor(num: Int): List[Int] = {
    val factors = ListBuffer.empty[Int]
    var i = 1
    val limit = math.sqrt(num.toDouble).toInt
    while (i <= limit) {
      if (num % i == 0) {
        factors += i
        factors += num / i
      }
      i += 1
    }
    factors.distinct.sorted.toList
  }

  def factorWithMap(num: Int): List[Int] = {
    if (factorMap.contains(num)) {
      return factorMap(num).toList
    }
    val f = factor(num)
    factorMap.put(num, f)
    f
  }

  def main(args: Array[String]) {
//    timer.time((1 to 1000000).foreach(factor), message="1")
//    timer.time((1 to 1000000).foreach(factorWithMap), message="2")
//    timer.time((1 to 1000000).foreach(factor), message="1")
//    timer.time((1 to 1000000).foreach(factorWithMap), message="2")
//    timer.time((1 to 1000000).foreach(factor), message="1")
//    timer.time((1 to 1000000).foreach(factorWithMap), message="2")
    (1 to 100).map(f => f -> factor(f)).foreach(f => println(f._1 + " " + f._2))
  }
}
