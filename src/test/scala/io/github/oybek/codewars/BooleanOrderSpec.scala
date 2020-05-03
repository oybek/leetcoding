package io.github.oybek.codewars

import java.math.BigInteger
import org.scalatest.{FlatSpec, Matchers}

class BooleanOrderSpec extends FlatSpec with Matchers {
  "Java solution" should "pass tests" in {
    assert(new BooleanOrder("tft","^&").solve() == new BigInteger("2"))
    assert(new BooleanOrder("ttftff","|&^&&").solve() == new BigInteger("16"))
    assert(new BooleanOrder("ttftfftf","|&^&&||").solve() == new BigInteger("339"))
    assert(new BooleanOrder("ttftfftft","|&^&&||^").solve() == new BigInteger("851"))
    assert(new BooleanOrder("ttftfftftf","|&^&&||^&").solve() == new BigInteger("2434"))
    assert(new BooleanOrder("ttftfftftffttfftftftfftft","|&^&&||^&&^^|&&||^&&||&^").solve() == new BigInteger("945766470799"))
  }

  "Scala solution" should "pass tests" in {
    BooleanOrderScala.solve("tft", "^&") should be (2L)
    BooleanOrderScala.solve("ttftff", "|&^&&") should be (16L)
    BooleanOrderScala.solve("ttftfftf", "|&^&&||") should be (339L)
    BooleanOrderScala.solve("ttftfftft", "|&^&&||^") should be (851L)
    BooleanOrderScala.solve("ttftfftftf", "|&^&&||^&") should be (2434L)
    BooleanOrderScala.solve("ttftfftftffttfftftftfftft", "|&^&&||^&&^^|&&||^&&||&^") should be (945766470799L)
  }
}
