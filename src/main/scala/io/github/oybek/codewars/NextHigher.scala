package io.github.oybek.codewars

object NextHigher {
  def nextHigher(n: Int): Int = {
    val s = n.toBinaryString
    val res = (s.reverse + "0").replaceFirst("10", "01").reverse
    val c = 1279.toBinaryString
    Integer.parseInt(res, 2)
  }
}
