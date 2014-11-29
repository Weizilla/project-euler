package problem021b

import akka.actor.{Actor, ActorLogging, Props}

class MasterActor() extends Actor with ActorLogging {
  var allFactors = Map.empty[Int, Int]
  var calcLimit = 0

  override def receive: Receive = {
    case Calculate(lower, upper) =>
      this.calcLimit = upper - lower + 1

      for (i <- lower to upper) {
        val findActor = context.actorOf(Props(new FindFactorActor(self)))
        findActor ! FindFactorSum(i)
      }

    case FindFactorSumResult(num, sum) =>
      allFactors += (num -> sum)
      if (allFactors.size == calcLimit) {
        log.info("All Factors Done. ")
        log.info("Sum: " + findSumOfPairs())
        context.system.shutdown()
      }
  }

  private def findSumOfPairs(): Int = {
    var allResults = Set.empty[Int]
    for {
      (k, v) <- allFactors
      if allFactors.getOrElse(v, null) == k && k != v
    } {
      allResults += k
      allResults += v
    }

    allResults.sum
  }
}

case class Calculate(lower: Int, upper: Int)
case class FindFactorSum(num: Int)
case class FindFactorSumResult(num: Int, sum: Int)
case class TestFactor(num: Int, factor: Int)
case class TestFactorResult(num: Int, factor: Int, isFactor: Boolean)
