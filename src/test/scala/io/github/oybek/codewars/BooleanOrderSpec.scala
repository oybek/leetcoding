package io.github.oybek.codewars

import java.math.BigInteger

import org.scalatest.funsuite.AnyFunSuite

class BooleanOrderSpec extends AnyFunSuite {
  test("BooleanOrder") {
    assert(new BigInteger("2") ==            new BooleanOrder("tft","^&").solve())
    assert(new BigInteger("16") ==           new BooleanOrder("ttftff","|&^&&").solve())
    assert(new BigInteger("339") ==          new BooleanOrder("ttftfftf","|&^&&||").solve())
    assert(new BigInteger("851") ==          new BooleanOrder("ttftfftft","|&^&&||^").solve())
    assert(new BigInteger("2434") ==         new BooleanOrder("ttftfftftf","|&^&&||^&").solve())
    assert(new BigInteger("945766470799") == new BooleanOrder("ttftfftftffttfftftftfftft","|&^&&||^&&^^|&&||^&&||&^").solve())
  }
}
