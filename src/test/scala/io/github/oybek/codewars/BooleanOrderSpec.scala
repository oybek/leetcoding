package io.github.oybek.codewars

import java.math.BigInteger

import org.scalatest.{FlatSpec, Matchers}

import scala.collection.mutable
import scala.util.Random

class BooleanOrderSpec extends FlatSpec with Matchers {
  "Java solution" should "pass tests" in {
    assert(new BOrder("tft","^&").solve() == new BigInteger("2"))
    assert(new BOrder("ttftff","|&^&&").solve() == new BigInteger("16"))
    assert(new BOrder("ttftfftf","|&^&&||").solve() == new BigInteger("339"))
    assert(new BOrder("ttftfftft","|&^&&||^").solve() == new BigInteger("851"))
    assert(new BOrder("ttftfftftf","|&^&&||^&").solve() == new BigInteger("2434"))
    assert(new BOrder("ttftfftftffttfftftftfftft","|&^&&||^&&^^|&&||^&&||&^").solve() == new BigInteger("945766470799"))
  }

  "Scala solution" should "pass sample tests" in {
    BooleanOrderScala.solve("tft", "^&") should be (2L)
    BooleanOrderScala.solve("ttftff", "|&^&&") should be (16L)
    BooleanOrderScala.solve("ttftfftf", "|&^&&||") should be (339L)
    BooleanOrderScala.solve("ttftfftft", "|&^&&||^") should be (851L)
    BooleanOrderScala.solve("ttftfftftf", "|&^&&||^&") should be (2434L)
    BooleanOrderScala.solve("ttftfftftffttfftftftfftft", "|&^&&||^&&^^|&&||^&&||&^") should be (945766470799L)
  }

  "Scala solution" should "pass random tests" in {
    import Tools._

    (1 to 100).map { _ =>
      val operandsNum = 1 + Random.nextInt(19)
      val operands = List.fill(operandsNum)("tft".oneOf).mkString
      val operators = List.fill(operandsNum-1)("&^|".oneOf).mkString
      BooleanOrderScala.solve(operands, operators) should be (Authors.solve(operands, operators))
    }
  }

  object Authors {

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
}

private object Tools {
  implicit class OneOf(val s: String) extends AnyVal {
    def oneOf: Char = s.charAt(Random.nextInt(s.length))
  }
  implicit class ArrayTools(val a: Array[Int]) extends AnyVal {
    def applySwaps(swaps: Iterable[(Int, Int)]): Array[Int] = {
      val b = a.clone
      swaps.foreach {
        case (x, y) =>
          val t = b(x)
          b(x) = b(y)
          b(y) = t
      }
      b
    }

    def isSorted: Boolean =
      a.sliding(2).forall {
        case Array(x, y) => x <= y
        case _ => true
      }

  }
}

