package problem021b

import akka.actor.{ActorSystem, Props}
import akka.util.Timeout
import common.timer

import scala.concurrent.duration._

/**
 * Same as problem 021 but hopefully better use of Actors
 */
object Main {
  def main(): Unit = {
    implicit val timeout = Timeout(100.seconds)

    val system = ActorSystem("problem021b")
    val master = system.actorOf(Props[MasterActor])

    master ! Calculate(1, 10000)

    system.awaitTermination()
  }

  def main(args: Array[String]) {
    timer.time(main)
  }
}
