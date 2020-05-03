package io.github.oybek.codewars

import java.util.Optional
import java.util.function.Predicate

import org.scalatest.{FlatSpec, Matchers}

class NextBigNumberSpec extends FlatSpec with Matchers {
  "NextBigNumber" should "pass sample tests" in {
    val fs = List(NextBigNumber.nextBiggerNumber(_), (x: Long) => 0L)

    fs.foreach { f =>
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

