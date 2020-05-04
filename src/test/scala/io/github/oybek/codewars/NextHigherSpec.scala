package io.github.oybek.codewars

import org.scalatest._

class KataSpec extends FunSuite with Matchers {
  test("Basic tests") {
    //assert(NextHigher.nextHigher(128) === 256)
    //assert(NextHigher.nextHigher(1) === 2)
    assert(NextHigher.nextHigher(1022) === 1279)
    //assert(NextHigher.nextHigher(127) === 191)
    //assert(NextHigher.nextHigher(1253343) === 1253359)
  }
}
