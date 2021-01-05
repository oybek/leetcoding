package io.github.oybek.codewars.jumpgameiv

object Solution extends App {
  def minJumps(arr: Array[Int]): Int = {
    println(
      arr.zipWithIndex
        .toList
        .groupBy(_._1)
        .view
        .mapValues(_.map(_._2))
        .toList
    )
    0
  }

  minJumps(Array(1, 3, 3, 4, 5))
}
