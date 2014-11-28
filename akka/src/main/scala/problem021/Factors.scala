package problem021

import akka.actor.{PoisonPill, Actor, ActorLogging, ActorRef, Props}
import problem021.Factors.{Amicable, FactorToLimit}

class Factors extends Actor with ActorLogging {
  var factorSums = Map.empty[Int, Int]
  var workers = Set.empty[ActorRef]
  var orgSender: ActorRef = _

  override def receive: Receive = {
    case FactorToLimit(limit) =>
      orgSender = sender
      for (i <- 1 to limit) {
        val f = context.actorOf(Props[FactorActor], "factor-actor-" + i)
        workers += f
        f ! FactorActor.Factor(i)
      }

    case FactorActor.FactorSum(num, result) =>
      factorSums += (num -> result)
      workers -= sender
      if (workers.isEmpty) {
        val pairs = findPairs()
        orgSender ! Amicable(pairs)
        self ! PoisonPill
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
