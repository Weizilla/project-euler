import scala.concurrent.duration.{SECONDS, NANOSECONDS, Duration}

abstract class common {
  def run(): Any

  def time[R](block: => R): R = {
    val t0: Long = System.nanoTime()
    val result = block
    val t1: Long = System.nanoTime()
    val duration = Duration(t1 - t0, NANOSECONDS)
    println("Elapsed time: " + duration.toUnit(SECONDS) + " seconds")
    result
  }

  def main(args: Array[String]) {
    println(time(run()))
  }
}
