package problem021

import akka.actor.{PoisonPill, ActorRef, Actor, ActorLogging, Props}
import problem021.FactorActor.{FactorSum, Factor}

import scala.math.sqrt

class FactorActor extends Actor with ActorLogging {
  var testFactors = Set.empty[Int]
  var goodFactors = Set(1)
  var orgSender: ActorRef = _
  var num: Int = _

  override def receive: Receive = {
    case Factor(num) => {
      orgSender = sender
      this.num = num

      testFactors = (2 to (sqrt(num.toDouble).toInt + 1)).toSet

      for (f <- testFactors) {
        val testFactor = context.actorOf(Props[FactorTestActor], s"test-actor-$num-$f")
        testFactor ! FactorTestActor.TestFactor(f, num)
      }
    }

    case FactorTestActor.Result(factor, isFactor) => {
      testFactors -= factor

      if (!isFactor) {
        goodFactors += factor
        goodFactors += num / factor
      }

      if (testFactors.isEmpty) {
        orgSender ! FactorSum(num, goodFactors.sum)
//        log.info("Finished factoring " + num + " sum : " + goodFactors)
        self ! PoisonPill
      }
    }
  }
}

object FactorActor {
  case class Factor(int: Int)
  case class FactorSum(num: Int, sum: Int)
}

