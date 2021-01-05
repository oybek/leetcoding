package io.github.oybek.codewars.ismyfriendcheating

object RemovedNumbers {

  val removNb: Long => List[(Long, Long)] =
    removNb3

  // First solution, brute force (Execution Timed Out)
  def removNb1(n: Long): List[(Long, Long)] = {
    val sum = (0L to n).sum
    val prs = for {
      a <- 0L to n
      b <- a + 1 to n if sum - a - b == a * b
    } yield (a, b)
    prs.toList.flatMap {
      case (x, y) => List((x, y), (y, x))
    }
  }

  // Second solution, brute force optimized somehow (Execution Timed Out)
  def removNb2(n: Long): List[(Long, Long)] = {
    val sum = (0L to n).sum
    val prs = for {
      a <- 0L to n
      b <- (a + 1 to n).dropWhile(x => sum-a-x != a*x).headOption
    } yield (a, b)
    prs.toList.flatMap {
      case (x, y) => List((x, y), (y, x))
    }
  }

  // Third solution, my stupid head realized that b can be calculated by formula
  def removNb3(n: Long): List[(Long, Long)] = {
    val sum = (0L to n).sum
    (0L to n)
      .filter(a => (sum-a)%(a+1) == 0 && (sum-a)/(a+1) <= n)
      .map(a => (a, (sum-a)/(a+1)))
      .toList
  }
}
