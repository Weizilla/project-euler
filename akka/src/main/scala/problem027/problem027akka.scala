package problem027

import akka.actor.{Actor, ActorLogging, ActorSystem, Props}
import akka.routing.RoundRobinPool
import akka.util.Timeout
import common.timer
import problem027b.{CalculateB, EvaluateB, EvaluationResultsB}

import scala.collection.immutable.SortedSet
import scala.collection.mutable.ArrayBuffer
import scala.concurrent.duration._

/** https://projecteuler.net/problem=27
 * Euler discovered the remarkable quadratic formula:

n² + n + 41

It turns out that the formula will produce 40 primes for the consecutive values n = 0 to 39.
However, when n = 40, 402 + 40 + 41 = 40(40 + 1) + 41 is divisible by 41, and certainly when
n = 41, 41² + 41 + 41 is clearly divisible by 41.

The incredible formula  n² − 79n + 1601 was discovered, which produces 80 primes for the
consecutive values n = 0 to 79. The product of the coefficients, −79 and 1601, is −126479.

Considering quadratics of the form:

n² + an + b, where |a| < 1000 and |b| < 1000

where |n| is the modulus/absolute value of n
e.g. |11| = 11 and |−4| = 4
Find the product of the coefficients, a and b, for the quadratic expression that produces
the maximum number of primes for consecutive values of n, starting with n = 0.

-59231
 */
object problem027akka {

  // too slow. faster to calculate if each is prime
  def calculatePrimes(limit: Int): Set[Int] = {
    var results = SortedSet(2 to limit: _*)
    var primes = Set.empty[Int]
    var n = 0

    while (results.nonEmpty) {
      n = results.head
      results -= n
      primes += n
      for {
        i <- 2 to ((limit / n) + 1)
      } {
        results -= n * i
      }
    }
    (primes + 1).toSet
  }

  def startActors(): Unit = {
    implicit val timeout = Timeout(100.seconds)

    val primes = calculatePrimes(1997002)

    println("Found " + primes.size + " primes")
    val system = ActorSystem("problem027")
    val master = system.actorOf(Props(new MasterActor(primes)))

    master ! CalculateB

    system.awaitTermination()
  }

  def main(args: Array[String]) {
    timer.time(startActors())
  }
}

case object Calculate
case class Evaluate(a: Int, b: Int)
case class EvaluationResults(a: Int, b: Int, numOfPrimes: Int)


class MasterActor(val primes: Set[Int]) extends Actor with ActorLogging {
  val worker = context.actorOf(Props(new WorkerActor(primes)).
    withRouter(RoundRobinPool(10)))
  var results = ArrayBuffer.empty[(Int, Int, Int)]
  var workers = Set.empty[(Int, Int)]
  var total = 0

  override def receive: Receive = {
    case CalculateB =>
      for {
        a <- -999 to 999
        b <- 2 to 999
        if primes(b) && primes(1 + a + b)
      } {
        worker ! EvaluateB(a, b)
        workers += ((a, b))
      }
      total = workers.size

    case EvaluationResultsB(a, b, numOfPrimes) => {
      workers -= ((a, b))
      results += ((a, b, numOfPrimes))

      if (workers.isEmpty) {
        log.info("GOT THEM ALL")
        val max = results.maxBy(_._3)
        log.info(max.toString() + " " + max._1 * max._2 )
        context.system.shutdown()
      }
    }
  }
}

class WorkerActor(val primes: Set[Int]) extends Actor with ActorLogging {
  override def receive: Actor.Receive = {
    case EvaluateB(a, b) => {
      var r = 1
      var n = 0
      do {
        r = n * n + n * a + b
        n += 1
      } while (primes(r))
      sender ! EvaluationResultsB(a, b, n - 1)
    }
  }
}
