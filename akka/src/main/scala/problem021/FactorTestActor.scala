package problem021

import akka.actor.{PoisonPill, Actor, ActorLogging}
import problem021.FactorTestActor.{Result, TestFactor}

class FactorTestActor extends Actor with ActorLogging {
  override def receive: Receive = {
    case TestFactor(factor, num) =>
      sender ! Result(factor, ! (num % factor == 0))
      self ! PoisonPill
  }
}

object FactorTestActor {
  case class TestFactor(factor: Int, num: Int)
  case class Result(factor: Int, isFactor: Boolean)
}
