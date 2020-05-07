package io.github.oybek.codewars

import org.scalatest.{FlatSpec, Matchers}

class SimpleAssemblerSpec extends FlatSpec with Matchers {
  "Assembler interpreter" should "pass all tests" in {
    {
      val program =
        Array("mov a 5", "inc a", "dec a", "dec a", "jnz a -1", "inc a")
      val registers =
        SimpleAssembler.interpret(program)
      registers should be(Map("a" -> 1))
    }

    {
      val program =
        Array("mov a -10", "mov b a", "inc a", "dec b", "jnz a -2")
      val registers =
        SimpleAssembler.interpret(program)
      registers should be(Map("a" -> 0, "b" -> -20))
    }
  }
}
