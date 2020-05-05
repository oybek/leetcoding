package io.github.oybek.codewars

import java.util.Optional
import java.util.function.Predicate

import org.scalatest.{FlatSpec, Matchers}

import scala.util.Random

class NextBigNumberSpec extends FlatSpec with Matchers {
  "NextBigNumber" should "pass sample tests" in {
    sampleTests(NextBigNumber.nextBiggerNumber)
  }

  "NextBigNumberScala" should "pass sample tests" in {
    sampleTests(NextBigNumberScala.nextBiggerNumber)
    (1 to 100) foreach { _ =>
      randomTest(NextBigNumberScala.nextBiggerNumber)
    }
  }

  def sampleTests(f: Long => Long): Unit = {
    assert(f(12)   ==   21)
    assert(f(144)  ==  414)
    assert(f(414)  ==  441)
    assert(f(513)  ==  531)
    assert(f(2017) == 2071)
    assert(f(21)   == -1)
    assert(f(111)  == -1)
    assert(f(717018444) == 717041448)
    assert(f(59884848459853L) == 59884848483559L)
    assert(f(600820990L) == 600829009L)
  }

  def randomTest(f: Long => Long): Unit = {
    val n = Random.nextLong(Long.MaxValue)
    f(n) == nextBiggerNumberA(n)
  }

  def nextBiggerNumberA(n: Long): Long = {
    def isSorted(s: String): Boolean =
      s.toList.sliding(2).forall {
        case Nil => true
        case _::Nil => true
        case x::y::_ => x <= y
      }
    val nsr = n.toString.reverse
    (for {
      in    <- (0 until nsr.length).find(i => !isSorted(nsr.slice(0, i+1)))
      slice  = nsr.substring(0, in)
      x      = nsr.substring(in, in + 1)(0)
      rest   = nsr.substring(in + 1)
      c     <- slice.find(_ > x)
      res    = slice.replaceFirst(c + "", x + "").sorted.reverse + c + rest
    } yield res.reverse.toLong).getOrElse(-1L)
  }

  "Tools" should "work correct" in {
    import NextBigNumber._

    isSorted("123") should be (true)
    isSorted("111112") should be (true)
    isSorted("11111") should be (true)
    isSorted("321") should be (false)

    NextBigNumber.sorted("321") should be ("123")
    NextBigNumber.sorted("eadf") should be ("adef")

    reversed("123") should be ("321")
    reversed("asdf") should be ("fdsa")
    reversed("0101") should be ("1010")

    findFirst("132", c => c > '1') should be (Optional.of('2'))
  }
}

