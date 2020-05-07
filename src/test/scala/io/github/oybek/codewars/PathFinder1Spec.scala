package io.github.oybek.codewars

import org.scalatest.{FlatSpec, Matchers}

class PathFinder1Spec extends FlatSpec with Matchers {

  // TODO: Replace examples and use TDD development by writing your own tests
  "PathFinder1" should "pass tests" in {
    val a = ".W.\n" + ".W.\n" + "..."
    val b = ".W.\n" + ".W.\n" + "W.."
    val c = "......\n" + "......\n" + "......\n" + "......\n" + "......\n" + "......"
    val d = "......\n" + "......\n" + "......\n" + "......\n" + ".....W\n" + "....W."
    assert(PathFinder1.pathFinder(a))
    assert(!PathFinder1.pathFinder(b))
    assert(PathFinder1.pathFinder(c))
    assert(!PathFinder1.pathFinder(d))
  }
}
