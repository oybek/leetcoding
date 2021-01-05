package io.github.oybek.codewars.wormadventures

import cats.implicits._

object Solution {

  def interpret(worm: List[(Int, Int)], dirs: List[Dir]): List[(Int, Int)] =
    dirs.map {
      case Right => (+1, 0)
      case Left => (-1, 0)
      case Up => (0, +1)
      case Down => (0, -1)
    }.foldLeft((worm.toVector, worm.toSet)) {
      case ((worm, cells), d) =>
        val newHead = worm.head |+| d
        if (!cells.contains(newHead) || (newHead == worm.last && worm.length > 2)) {
          (newHead +: worm.init, cells - worm.last + newHead)
        } else {
          (worm, cells)
        }
    }._1.toList
}
