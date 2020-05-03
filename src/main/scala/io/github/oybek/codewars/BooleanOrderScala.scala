package io.github.oybek.codewars

import scala.collection.mutable

object BooleanOrderScala {

  def solve(operands: String, operators: String): Long = {
    lazy val _solve : ((Boolean, Int, Int)) => Long = memoize {
      case ( true, i, j) if i == j => if (operands(i) == 't') 1 else 0
      case (false, i, j) if i == j => if (operands(i) == 'f') 1 else 0
      case (v, i, j) =>
        (i until j).map(k =>
          operators(k) match {
            case '&' => if (v) T(i, k) * T(k + 1, j)
                        else   A(i, k) * A(k + 1, j) - T(i, k) * T(k + 1, j)

            case '|' => if (v) A(i, k) * A(k + 1, j) - F(i, k) * F(k + 1, j)
                        else   F(i, k) * F(k + 1, j)

            case '^' => if (v) F(i, k) * T(k + 1, j) + T(i, k) * F(k + 1, j)
                        else   T(i, k) * T(k + 1, j) + F(i, k) * F(k + 1, j)
          }
        ).sum
    }

    def A(i: Int, j: Int): Long = T(i, j) + F(i, j)
    def T(i: Int, j: Int): Long = _solve( true, i, j)
    def F(i: Int, j: Int): Long = _solve(false, i, j)

    _solve(true, 0, operands.length-1)
  }

  def memoize[I, O](f: I => O): I => O = new mutable.HashMap[I, O]() {
    override def apply(key: I) = getOrElseUpdate(key, f(key))
  }
}
