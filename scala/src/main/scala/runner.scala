import scala.concurrent.duration.{SECONDS, NANOSECONDS, Duration}

abstract class runner {
  def run(): Any

  def time[R](block: => R, message: String = ""): R = {
    val t0: Long = System.nanoTime()
    val result = block
    val t1: Long = System.nanoTime()
    val duration = Duration(t1 - t0, NANOSECONDS)
    println("Elapsed time: " + duration.toUnit(SECONDS) + " seconds " + message)
    result
  }

  def main(args: Array[String]) {
    println(time(run()))
  }
}
