package problem021b

import akka.actor.{ActorLogging, Actor}

class TestFactorActor extends Actor with ActorLogging {
  override def receive: Receive = {
    case TestFactor(num, factor) =>
//      log.info(s"Testing $num with factor $factor")
      sender ! TestFactorResult(num, factor, num % factor == 0)
      context.stop(self)
  }
}
