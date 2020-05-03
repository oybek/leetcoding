package io.github.oybek.leetcode

import NQueens.{King, Queen}

// Solution of: https://leetcode.com/problems/queens-that-can-attack-the-king/
object QueensThatCanAttackTheKing extends App {

  def queensAttacktheKing(aQueens: Array[Array[Int]], aKing: Array[Int]): List[List[Int]] = {
    val king = King(aKing(0), aKing(1))
    val queens = aQueens.collect { case xs if xs.length == 2 => Queen(xs(0), xs(1)) }
    queens
      .filter(_.doesAttack(king, queens))
      .toList
      .map(q => List(q.x, q.y))
  }

  assert(
    queensAttacktheKing(
      Array(Array(2, 2), Array(3, 4), Array(4, 4)),
      Array(3, 3)
    ) == List(List(2, 2), List(3, 4), List(4, 4)),
  )
}
