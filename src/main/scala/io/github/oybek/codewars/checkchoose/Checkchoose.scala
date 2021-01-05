package io.github.oybek.codewars.checkchoose

import scala.collection.mutable

object Checkchoose {

  def checkchoose(m: Long, n: Int): Long =
    LazyList
      .from(0)
      .takeWhile(_ <= n)
      .map(x => (x, fact(n) / fact(x) / fact(n - x)))
      .takeWhile(_._2 <= m)
      .maxByOption(_._2)
      .fold(-1) { case (x, y) => if (m == y) x else -1 }

  lazy val fact: Int => Long = memoize {
    case 0 => 1L
    case n => n * fact(n-1)
  }

  def memoize[I, O](f: I => O): I => O = new mutable.HashMap[I, O]() {
    override def apply(key: I): O = getOrElseUpdate(key, f(key))
  }
}
