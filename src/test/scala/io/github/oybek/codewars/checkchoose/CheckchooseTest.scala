package io.github.oybek.codewars.checkchoose

import io.github.oybek.codewars.checkchoose.CheckchooseTest._
import org.scalatest.Assertions._
import org.scalatest._

class CheckchooseTest extends FlatSpec {
  it should "pass basic tests" in {
    dotest(6, 4, 2)
    dotest(4, 4, 1)
    dotest(4, 2, -1)
    dotest(35, 7, 3)
    dotest(3268760, 25, 10)
  }
}

object CheckchooseTest {

  private def dotest(m: Long, n: Int, expect: Long): Unit = {
    println("Testing: " + m + ", " + n )
    val actual: Long = Checkchoose.checkchoose(m, n)
    println("Actual: " + actual)
    println("Expect: " + expect)
    println("*")
    assertResult(expect){actual}
  }
}
