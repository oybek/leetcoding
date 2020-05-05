package io.github.oybek.codewars

import java.util.Optional

object NextBigNumberScala {

  def nextBiggerNumber(n: Long): Long = {
    val nsr = n.toString.reverse
    (for {
      in    <- (0 until nsr.length).find(i => !nsr.slice(0, i+1).isSorted)
      slice  = nsr.substring(0, in)
      x      = nsr.substring(in, in + 1)(0)
      rest   = nsr.substring(in + 1)
      c     <- slice.find(_ > x)
      res    = slice.replaceFirst(s"$c", s"$x").sorted.reverse + c + rest
    } yield res.reverse.toLong).getOrElse(-1L)
  }

  implicit class StringTools(val s: String) extends AnyVal {
    def isSorted: Boolean =
      s.toList.sliding(2).forall {
        case Nil => true
        case _::Nil => true
        case x::y::_ => x <= y
      }
  }
}
