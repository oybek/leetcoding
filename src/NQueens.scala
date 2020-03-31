
// Solution of: https://leetcode.com/problems/n-queens/
object NQueens extends App {

  // I think reusable code
  sealed trait ChessPiece {
    def x: Int
    def y: Int
    def doesAttack(chessPiece: ChessPiece): Boolean
  }

  case class Queen(x: Int, y: Int) extends ChessPiece {
    def doesAttack(target: ChessPiece): Boolean =
      x == target.x || y == target.y || (x - target.x).abs == (y - target.y).abs
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
