package io.github.oybek.codewars.ismyfriendcheating

import org.scalatest._
import org.scalatest.Assertions._

import RemovedNumbersTest._

class RemovedNumbersTest extends FlatSpec {
  it should "pass basic tests" in {
    testing(26, List((15, 21), (21, 15)))
    testing(100, List())
    testing(101, List((55, 91), (91, 55)))

  }
}

object RemovedNumbersTest {

  private def ListTupleToString(res: List[(Long, Long)]): String = {
    var s: String = "["
    val l: Int = res.size
    for (i <- 0 until l) {
      val a = res(i)
      s += "(" + a._1 + ", " + a._2
      if (i < l - 1) s += "), " else s += ")"
    }
    s += "]"
    return s
  }
  private def testing(n: Long, expect: List[(Long, Long)]): Unit = {
    println("Testing: " + n)
    val actual: List[(Long, Long)] = RemovedNumbers.removNb(n)
    println("Actual: " + ListTupleToString(actual))
    println("Expect: " + ListTupleToString(expect))
    println("-")
    assertResult(expect){actual}
  }
}
