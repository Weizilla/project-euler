package problem022

import akka.actor.{Actor, ActorLogging, ActorSystem, Props}
import akka.routing.SmallestMailboxPool
import akka.util.Timeout
import common.Timer

import scala.collection.mutable.ArrayBuffer
import scala.concurrent.duration._

/**
 * Same as problem 021 but hopefully better use of Actors
 */
object problem022 extends Timer {
  def main(): Unit = {
    implicit val timeout = Timeout(100.seconds)

    val system = ActorSystem("problem022")
    val master = system.actorOf(Props[MasterActor])

    master ! Calculate

    system.awaitTermination()
  }

  def main(args: Array[String]) {
    time(main)
  }
}

case object Calculate
case class CalculateScore(index: Int, name: String)
case class CalculationResult(result: Int)

class MasterActor extends Actor with ActorLogging {
  var names: Array[(String, Int)] = _
  val worker = context.actorOf(Props[WorkerActor].withRouter(SmallestMailboxPool(10)))
  val results = ArrayBuffer.empty[Int]

  override def receive: Receive = {
    case Calculate => {
      val path = getClass.getResourceAsStream("/problem022/p022_names.txt")
      names = io.Source.fromInputStream(path).mkString.
        split(",").map(_.replace("\"", "")).sorted.zipWithIndex

      for ((n, i) <- names) {
        worker ! CalculateScore(i + 1, n)
      }
    }

    case CalculationResult(result) =>
      results += result
      if (results.size == names.size) {
        log.info("SUM: " + results.sum)
        context.system.shutdown()
      }
  }
}

class WorkerActor extends Actor with ActorLogging {
  override def receive: Actor.Receive = {
    case CalculateScore(i, n) =>
      val sum = n.map(_.toInt - 'A'.toInt + 1).sum
      sender ! CalculationResult(i * sum)
  }
}

