package problem021
import akka.actor.{ActorSystem, Props}
import akka.pattern.ask
import akka.util.Timeout
import common.timer
import problem021.Factors.Amicable

import scala.concurrent.Await
import scala.concurrent.duration._

/**
 * https://projecteuler.net/problem=21
 * Let d(n) be defined as the sum of proper divisors of n (numbers less than n which divide evenly into n).
If d(a) = b and d(b) = a, where a ≠ b, then a and b are an amicable pair and each of a and b are called amicable numbers.

For example, the proper divisors of 220 are 1, 2, 4, 5, 10, 11, 20, 22, 44, 55 and 110; therefore d(220) = 284. The proper divisors of 284 are 1, 2, 4, 71 and 142; so d(284) = 220.

Evaluate the sum of all the amicable numbers under 10000.


Answer:
31626
 */

object Main {
  def main(): Int = {
    implicit val timeout = Timeout(100.seconds)

    val system = ActorSystem("problem021")
    val factorActor = system.actorOf(Props[Factors])
    val future = (factorActor ? Factors.FactorToLimit(10000)).mapTo[Amicable]

    val result = Await.result(future, timeout.duration).pairs.sortBy(f => f._1)
    println(s"RESULT: " + result.mkString("\n"))

    var allResults = Set.empty[Int]
    for {
      (p1, p2) <- result
    } {
      allResults += p1
      allResults += p2
    }

    val sum: Int = allResults.sum
    println("FINAL: " + sum)

    system.awaitTermination()
    sum
  }

  def main(args: Array[String]): Unit = {
    timer.time(main)
  }
}
