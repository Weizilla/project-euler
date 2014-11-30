package problem027b

import akka.actor.{Actor, ActorLogging, ActorSystem, Props}
import akka.routing.RoundRobinPool
import akka.util.Timeout
import common.timer

import scala.collection.mutable.ArrayBuffer
import scala.concurrent.duration._

/**
* same as problem027akka except doesn't have prime look up table
*/
object problem027akkab {

  def isPrime(num: Int): Boolean = {
    if (num < 2) {
      false
    } else {
      (2 to (math.sqrt(num.toDouble).toInt + 1)).forall(num % _ != 0)
    }
  }

  def startActors(): Unit = {
    implicit val timeout = Timeout(100.seconds)

    val system = ActorSystem("problem027b")
    val master = system.actorOf(Props(new MasterActorB()))

    master ! CalculateB

    system.awaitTermination()
  }

  def main(args: Array[String]) {
    timer.time(startActors())
  }
}

case object CalculateB
case class EvaluateB(a: Int, b: Int)
case class EvaluationResultsB(a: Int, b: Int, numOfPrimes: Int)


class MasterActorB() extends Actor with ActorLogging {
  val worker = context.actorOf(Props(new WorkerActorB()).
    withRouter(RoundRobinPool(10)))
  var results = ArrayBuffer.empty[(Int, Int, Int)]
  var workers = Set.empty[(Int, Int)]
  var total = 0

  override def receive: Receive = {
    case CalculateB => {
      for {
        a <- -999 to 999
        b <- 2 to 999
        if problem027akkab.isPrime(b) && problem027akkab.isPrime(1 + a + b)
      } {
        worker ! EvaluateB(a, b)
        workers += ((a, b))
      }
      total = workers.size
    }

    case EvaluationResultsB(a, b, numOfPrimes) => {
      {
        workers -= ((a, b))
        results += ((a, b, numOfPrimes))

        if (workers.isEmpty) {
          log.info("GOT THEM ALL B")
          val max = results.maxBy(_._3)
          log.info(max.toString() + " " + max._1 * max._2)
          context.system.shutdown()
        }
      }
    }
  }
}

class WorkerActorB() extends Actor with ActorLogging {
  override def receive: Actor.Receive = {
    case EvaluateB(a, b) => {
      {
        var r = 1
        var n = 0
        do {
          r = n * n + n * a + b
          n += 1
        } while (problem027akkab.isPrime(r))
        sender ! EvaluationResultsB(a, b, n - 1)
      }
    }
  }
}
