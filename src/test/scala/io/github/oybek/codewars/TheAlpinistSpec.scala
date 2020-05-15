package io.github.oybek.codewars

import org.scalatest.{FlatSpec, Matchers}

class TheAlpinistSpec extends FlatSpec with Matchers {
  "solution" should "pass tests" in {
    val a = "700\n"+
            "770\n"+
            "077"

    val b = "010\n"+
            "010\n"+
            "010"

    val c = "010\n"+
            "101\n"+
            "010"

    val d = "0707\n"+
            "7070\n"+
            "0707\n"+
            "7070"

    val e = "700000\n"+
            "077770\n"+
            "077770\n"+
            "077770\n"+
            "077770\n"+
            "000007"

    val f = "777000\n"+
            "007000\n"+
            "007000\n"+
            "007000\n"+
            "007000\n"+
            "007777"

    val g = "000000\n"+
            "000000\n"+
            "000000\n"+
            "000010\n"+
            "000109\n"+
            "001010"

    val h =
      "747062171\n"+
      "904866986\n"+
      "340268947\n"+
      "974542098\n"+
      "574036106\n"+
      "567149757\n"+
      "726658263\n"+
      "848862682\n"+
      "755104617"

    TheAlpinist.pathFinder(a) should be (0)
    TheAlpinist.pathFinder(b) should be (2)
    TheAlpinist.pathFinder(c) should be (4)
    TheAlpinist.pathFinder(d) should be (42)
    TheAlpinist.pathFinder(e) should be (14)
    TheAlpinist.pathFinder(f) should be (0)
    TheAlpinist.pathFinder(g) should be (4)
    TheAlpinist.pathFinder(h) should be (34)
  }
}
