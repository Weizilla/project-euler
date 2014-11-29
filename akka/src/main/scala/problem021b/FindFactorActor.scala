package problem021b

import akka.actor.{Actor, ActorLogging, ActorRef}

class FindFactorActor(val master: ActorRef) extends Actor with ActorLogging {
  var factors = Set.empty[Int]
  var goodFactors = Set(1)

  override def receive: Receive = {
    case FindFactorSum(num) => {
      //      log.info(s"Finding factors for $num")

      factors = (2 to (math.sqrt(num.toDouble).toInt + 1)).toSet
      for (i <- factors) {
        if (num % i == 0) {
          goodFactors += i
          goodFactors += num / i
        }
      }


      sender ! FindFactorSumResult(num, goodFactors.sum)
      context.stop(self)
    }
  }
}
