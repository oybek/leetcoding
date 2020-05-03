package io.github.oybek.leetcode

import AddBinary._
import org.scalatest.{FlatSpec, Matchers}

class AddBinarySpec  extends FlatSpec with Matchers {
  "AddBinary" should "pass test" in {
    assert(addBinary("1", "1") == "10")
    assert(addBinary("11", "1") == "100")
    assert(addBinary("1010", "1011") == "10101")
  }
}
