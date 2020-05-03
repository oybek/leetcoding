package io.github.oybek.codewars

import java.util.Optional
import java.util.function.Predicate

import org.scalatest.{FlatSpec, Matchers}

class NextBigNumberSpec extends FlatSpec with Matchers {
  import NextBigNumber._

  "NextBigNumber" should "pass sample tests" in {
    assert(nextBiggerNumber(12)   ==   21)
    assert(nextBiggerNumber(144)  ==  414)
    assert(nextBiggerNumber(414)  ==  441)
    assert(nextBiggerNumber(513)  ==  531)
    assert(nextBiggerNumber(2017) == 2071)
  }

  "NextBigNumber" should "pass my tests" in {
    assert(nextBiggerNumber(21)  == -1)
    assert(nextBiggerNumber(111) == -1)
  }

  "NextBigNumber" should "pass codewars tests" in {
    assert(nextBiggerNumber(717018444) == 717041448)
    assert(nextBiggerNumber(59884848459853L) == 59884848483559L)
    assert(nextBiggerNumber(600820990L) == 600829090L)
  }

  "Tools" should "work correct" in {
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

