package io.github.oybek.codewars.wormadventures

import org.scalatest.{FlatSpec, Matchers}

import cats.implicits._
import scala.util.Random

class SolutionSpec extends FlatSpec with Matchers {

  "issue" should "be fixed" in {
    {
      val worm = List((0, 0), (0, -1), (0, -2), (1, -2), (2, -2), (2, -1), (2, 0), (2, 1), (2, 2), (1, 2), (0, 2), (0, 1))
      val ans = Solution.interpret(worm, List(Up))
      ans should be (List((0,1), (0,0), (0,-1), (0,-2), (1,-2), (2,-2), (2,-1), (2,0), (2,1), (2,2), (1,2), (0,2)))
    }
    {
      val worm = List((0, 0), (0, -1), (1, -1), (2, -1), (2, 0), (2, 1), (1, 1), (0, 1))
      val ans = Solution.interpret(worm, List(Up))
      ans should be (List((0,1), (0,0), (0,-1), (1,-1), (2,-1), (2,0), (2,1), (1,1)))
    }
    {
      val worm = List((0, 0), (0, 1))
      val ans = Solution.interpret(worm, List(Up))
      ans should be (worm)
    }
  }

  "solution" should "pass tests" in {
    Solution.interpret(
      List((-2,-3), (-2,-2), (-2,-1), (-1,-1), (0,-1), (0,0)),
      List(Down, Right, Left, Up, Down, Down, Right, Right, Left, Up, Right, Left, Down)
    ) should be (List((2,-3), (2,-2), (1,-2), (1,-3), (0,-3), (-1,-3)))

    Solution.interpret(
      List((0, 0), (0, 1), (0, 2)),
      List(Left)
    ) should be(List((-1, 0), (0, 0), (0, 1)))

    Solution.interpret(
      List((0, 0), (1, 0), (2, 0), (2, 1)),
      List(Down)
    ) should be(List((0, -1), (0, 0), (1, 0), (2, 0)))

    Solution.interpret(
      List((0, 0), (1, 0), (2, 0), (2, 1)),
      List(Down)
    ) should be(List((0, -1), (0, 0), (1, 0), (2, 0)))
  }

  "solution" should "pass small tests" in {
    {
      val worm = List((0, -3), (1, -3), (1, -2), (2, -2), (2, -1), (1, -1), (0, -1), (0, 0))
      val dirs = List(Up, Up, Right, Right, Left)
      Solution.interpret(worm, dirs) should be(AuthorSolution.interpret(worm, dirs))
    }

    (1 to 10).foreach { _ =>
      val worm = genWorm(Random.between(3, 15))
      val dirs = List.fill(Random.between(3, 20))(Random.shuffle(List(Left, Right, Down, Up)).head)
      Solution.interpret(worm, dirs) should be (AuthorSolution.interpret(worm, dirs))
    }
  }

  "solution" should "pass medium tests" in {
    (1 to 10).foreach { _ =>
      val worm = genWorm(Random.between(15, 30))
      val dirs = List.fill(Random.between(3, 40))(Random.shuffle(List(Left, Right, Down, Up)).head)
      Solution.interpret(worm, dirs) should be (AuthorSolution.interpret(worm, dirs))
    }
  }

  "solution" should "pass tricky some cases" in {
    Solution.interpret(
      List((0, 0), (0, 1), (1, 1), (1, 0)),
      List(Right)
    ) should be (List((1, 0), (0, 0), (0, 1), (1, 1)))

    Solution.interpret(
      List((0, 0), (0, 1)),
      List(Up)
    ) should be (List((0, 0), (0, 1)))
  }

  "solution" should "pass big tests" in {
    (1 to 10).foreach { _ =>
      val worm = genWorm(Random.between(10, 100))
      val dirs = List.fill(Random.between(10, 200))(Random.shuffle(List(Left, Right, Down, Up)).head)
      Solution.interpret(worm, dirs) should be (AuthorSolution.interpret(worm, dirs))
    }
  }

  "solution" should "pass load tests" in {
    (1 to 5).foreach { _ =>
      val worm = genWorm(10000, List(Up, Right))
      val dirs = List.fill(7000)(Random.shuffle(List(Left, Down)).head)
      Solution.interpret(worm, dirs) should be(AuthorSolution.interpret(worm, dirs))
    }
  }

  private def genWorm(n: Int, dirs: List[Dir] = List(Up, Down, Left, Right)): List[(Int, Int)] =
    (1 to n).foldLeft((List((0, 0)), Set((0, 0)))) {
      case ((worm@head :: _, cells), _) =>
        Random.shuffle(
          dirs.map {
            case Right => head |+| (+1, 0)
            case Left  => head |+| (-1, 0)
            case Down  => head |+| (0, -1)
            case Up    => head |+| (0, +1)
          }
        ).find(!cells.contains(_)).map(c =>
          (c :: worm, cells + c)
        ).getOrElse((worm, cells))
    }._1

  implicit class Ops(val cell: (Int, Int)) {
    def left = cell.copy(_1 = cell._1 - 1)
    def right = cell.copy(_1 = cell._1 + 1)
    def down = cell.copy(_2 = cell._2 - 1)
    def up = cell.copy(_2 = cell._2 + 1)
  }

  private object AuthorSolution {
    def interpret(worm: List[(Int, Int)], dirs: List[Dir]): List[(Int, Int)] =
      dirs.foldLeft((worm.toVector, worm.toSet)) {
        case ((worm, cells), dir) =>
          def isHappy(head: (Int, Int)): Boolean =
            !cells.contains(head) || head == worm.last
          val head = worm.head
          val newHeadOpt = dir match {
            case Right if isHappy(head.right) => Some(head.right)
            case Left if isHappy(head.left) => Some(head.left)
            case Down if isHappy(head.down) => Some(head.down)
            case Up if isHappy(head.up) => Some(head.up)
            case _ => None
          }
          newHeadOpt.fold((worm, cells))(
            newHead => (newHead +: worm.dropRight(1), cells - worm.last + newHead)
          )
      }._1.toList
  }
}
