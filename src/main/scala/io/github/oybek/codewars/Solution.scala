package io.github.oybek.codewars

import java.time.Clock

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object FutureApp extends App {

  val x = List(Future(1+100), Future(1+2))

  val y = x.foldLeft(Future.successful(List.empty[Int])) {
    case (acc, cur) =>
      for {
        x <- acc
        y <- cur
      } yield y::x
  }

  val computation = Future { 1 + 1 }

  val begin = System.currentTimeMillis()
  for {
    _ <- computation
    end = System.currentTimeMillis()
  } yield (begin, end, begin - end)

  computation.isCompleted
}

