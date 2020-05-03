package io.github.oybek.leetcode

// Solution of: https://leetcode.com/problems/n-queens-ii/
object NQueens2 extends App {
  def totalNQueens(n: Int): Int =
    NQueens.solveNQueens(n).length

  assert(totalNQueens(4) == 2)
  assert(totalNQueens(3) == 0)
}
