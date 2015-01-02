package common

import scala.concurrent.duration._

object timer {
  def time[R](block: => R, message: String = ""): R = {
    val t0: Long = System.nanoTime()
    val result = block
    val t1: Long = System.nanoTime()
    val duration = Duration(t1 - t0, NANOSECONDS)
    println("Elapsed time: " + duration.toUnit(MILLISECONDS) + "ms " + message)
    result
  }
}
