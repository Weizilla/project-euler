

/**
https://projecteuler.net/problem=15
Starting in the top left corner of a 2×2 grid, and only
being able to move to the right and down, there are exactly 6 routes to the bottom right corner.

How many such routes are there through a 20×20 grid?
 */
object problem015 extends runner {
  def calcRoutes(limit: Int): List[List[Int]] = {
    val paths = time(genPaths(limit * 2), " gen paths")
    time(paths.filter(valid(limit, _)), " filter")
  }

  def valid(limit: Int, path: List[Int]): Boolean = {
    move(Point(0, 0), limit, path)
  }

  def move(point: Point, limit: Int, path: List[Int]): Boolean = {
    if (! point.valid(limit)) return false
    path match {
      case 0 :: t => move(point.moveRight(), limit, t)
      case 1 :: t => move(point.moveDown(), limit, t)
      case Nil => true
    }
  }

  def genPaths(num: Int): List[List[Int]]= {
    def genPaths(num: Int, acc: List[List[Int]]): List[List[Int]] = {
      if (num == 0) {
        acc
      } else {
        val newAcc = acc.map(List(0) ::: _) ::: acc.map(List(1) ::: _)
        genPaths(num - 1, newAcc)
      }
    }
    genPaths(num, List(List()))
  }

  override def run(): Any = {
    val limit = 5
    calcRoutes(limit).size
  }
}

case class Point(r: Int, c: Int) {
  def valid(limit: Int): Boolean = {
    r <= limit && c <= limit
  }
  def moveRight(): Point = Point(r, c + 1)
  def moveDown(): Point = Point(r + 1, c)
}
