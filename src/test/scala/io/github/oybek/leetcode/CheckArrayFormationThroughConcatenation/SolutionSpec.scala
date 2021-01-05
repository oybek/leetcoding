package io.github.oybek.leetcode.CheckArrayFormationThroughConcatenation

import org.scalatest.{FlatSpec, Matchers}

class SolutionSpec extends FlatSpec with Matchers {
  it should "pass example tests" in {
    Solution.canFormArray(Array(85), Array(Array(85))) shouldBe true
    Solution.canFormArray(Array(15, 85), Array(Array(85), Array(15))) shouldBe true
    Solution.canFormArray(Array(49, 18, 16), Array(Array(16, 18, 49))) shouldBe false
    Solution.canFormArray(Array(91, 4, 64, 78), Array(Array(78), Array(4, 64), Array(91))) shouldBe true
    Solution.canFormArray(Array(1,3,5,7), Array(Array(2,4,6,8))) shouldBe false
  }
}
