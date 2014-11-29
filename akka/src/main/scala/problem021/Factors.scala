package problem021

import akka.actor.{Actor, ActorLogging, ActorRef, Props}
import akka.routing.RoundRobinPool
import problem021.Factors.{Amicable, FactorToLimit}

class Factors extends Actor with ActorLogging {
  var factorSums = Map.empty[Int, Int]
  var factors = Set.empty[Int]
  var orgSender: ActorRef = _

  override def receive: Receive = {
    case FactorToLimit(limit) =>
      orgSender = sender
      val f = context.actorOf(Props[FactorActor].withRouter(RoundRobinPool(10)))
      for (i <- 1 to limit) {
        factors += i
        f ! FactorActor.Factor(i)
      }

    case FactorActor.FactorSum(num, result) =>
      factorSums += (num -> result)
      factors -= num
      if (factors.isEmpty) {
        val pairs = findPairs()
        orgSender ! Amicable(pairs)
        context.system.shutdown()
      }
  }

  private def findPairs(): List[(Int, Int)] = {
    (for {
      (k, v) <- factorSums
      if factorSums.getOrElse(v, null) == k && k != v
    }  yield (k, v)).toList
  }
}

object Factors {
  case class FactorToLimit(limit: Int)
  case class Amicable(pairs: List[(Int, Int)])
}
