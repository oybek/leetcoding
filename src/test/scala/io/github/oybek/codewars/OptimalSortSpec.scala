package io.github.oybek.codewars

import org.scalatest.{FlatSpec, Matchers}

import scala.util.Random

class OptimalSortSpec extends FlatSpec with Matchers {
  import Tools._

  "Optimal sort" should "pass sample tests" in {
    OptimalSort.sort(Array(1, 2, 3)) should be (Array())
    OptimalSort.sort(Array(3, 2, 1)) should be (Array((0, 2)))

    val a = Array(4, 3, 2, 1)
    val swaps = OptimalSort.sort(a.clone)
    //a.applySwaps(swaps).isSorted should be (true)
    swaps.length should be (2)
  }

  "Optimal sort" should "pass tests" in {
    OptimalSort.sort(Array()) should be (Array())
    OptimalSort.sort(Array(1)) should be (Array())
    OptimalSort.sort(Array(1, 2)) should be (Array())

    {
      val a = Array(3, 7, 1, 4, 5, 9)
      val swaps = OptimalSort.sort(a.clone)
      a.applySwaps(swaps).isSorted should be(true)
      swaps.length should be(4)
    }

    (1 to 100).foreach { _ =>
      val a = Array.fill(Random.nextInt(100))(Random.nextInt)

      val swaps = OptimalSort.sort(a.clone)
      val mySwaps = sort(a.clone)

      a.applySwaps(swaps).isSorted should be(true)
      a.applySwaps(mySwaps).isSorted should be(true)

      assert(swaps.length <= mySwaps.length)
    }
  }

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
