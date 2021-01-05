package io.github.oybek.leetcode.BeautifulArrangement

import org.scalatest.{FlatSpec, Matchers}

class SolutionSpec extends FlatSpec with Matchers {
  it should "pass example tests" in {
    Solution.countArrangement(2) shouldBe 2
  }
}
