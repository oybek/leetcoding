package io.github.oybek.leetcode

import io.github.oybek.leetcode.AddBinary._
import org.scalatest.funsuite.AnyFunSuite

class AddBinarySpec extends AnyFunSuite {
  test("AddBinary") {
    assert(addBinary("1", "1") == "10")
    assert(addBinary("11", "1") == "100")
    assert(addBinary("1010", "1011") == "10101")
  }
}
