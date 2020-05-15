package io.github.oybek.codewars

import org.scalatest.{FlatSpec, Matchers}

class ShortestKnightPathSpec extends FlatSpec with Matchers {
  "solution" should "pass tests" in {
    ShortestKnightPath.knight("a1", "c1") should be (2)
    ShortestKnightPath.knight("a1", "f1") should be (3)
    ShortestKnightPath.knight("a1", "f3") should be (3)
    ShortestKnightPath.knight("a1", "f4") should be (4)
    ShortestKnightPath.knight("a1", "f7") should be (5)
  }
}
