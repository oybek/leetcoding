package io.github.oybek.codewars

object OptimalSort {

  def sort(a: Array[Int]): Array[(Int, Int)] = {
    val as = a.sorted

    def getSwap(is: List[Int]): Option[(Int, Int)] = {
      is
        .combinations(2)
        .to(LazyList)
        .map { case List(x, y) => (x, y) }
        .find { case (x, y) => a(x) == as(y) && a(y) == as(x) }
        .orElse {
          for {
            i <- is.headOption
            j <- is.find(x => as(x) == a(i))
          } yield (i, j)
        }
    }

    def findSwaps(swaps: List[(Int, Int)], is: List[Int]): List[(Int, Int)] = {
      val swapOpt = getSwap(is)
      swapOpt match {
        case None => swaps
        case Some((x, y)) =>

          val t = a(x)
          a(x) = a(y)
          a(y) = t

          findSwaps((x, y)::swaps, is.filterNot(i => a(i) == as(i)))
      }
    }

    val is = a.indices.filterNot(i => a(i) == as(i)).toList
    findSwaps(List.empty[(Int, Int)], is).reverse.toArray
  }

}
