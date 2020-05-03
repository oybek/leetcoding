package io.github.oybek.leetcode

// Solution of: https://leetcode.com/problems/n-queens/
object NQueens extends App {

  // I think reusable code
  sealed trait ChessPiece {
    def x: Int
    def y: Int
    def doesAttack(chessPiece: ChessPiece, obstacles: Iterable[ChessPiece] = List()): Boolean
  }

  implicit class NumberUtils(val n: Int) extends AnyVal {
    def between(x: Int, y: Int): Boolean =
      (n > x && n < y) || (n > y && n < x)
  }

  case class Queen(x: Int, y: Int) extends ChessPiece {
    def doesAttack(target: ChessPiece, obstacles: Iterable[ChessPiece] = List()): Boolean = {
      import math._
      val os = obstacles.filter(x => x != target && x != this)
      (x == target.x && !os.exists(o => x == o.x && o.y.between(y, target.y))) ||
      (y == target.y && !os.exists(o => y == o.y && o.x.between(x, target.x))) ||
      (abs(x - target.x) == abs(y - target.y) &&
        !os.exists(
          o => abs(x - o.x) == abs(y - o.y) && o.x.between(x, target.x) && o.y.between(y, target.y)
        )
      )
    }
  }

  case class King(x: Int, y: Int) extends ChessPiece {
    def doesAttack(target: ChessPiece, obstacles: Iterable[ChessPiece] = List()): Boolean = ???
  }

  // Problem specific part
  def solveNQueens(n: Int,
                   curRow: Int = 0,
                   placed: List[ChessPiece] = List.empty[ChessPiece]): List[List[String]] =
    if (curRow == n) {
      List(placed.map(q => ("." * n).updated(q.y, 'Q')))
    } else {
      (0 until n).toList filterNot (col =>
        placed.exists(_.doesAttack(Queen(curRow, col)))
      ) flatMap (col =>
        solveNQueens(n, curRow+1, Queen(curRow, col)::placed)
      )
    }
}
