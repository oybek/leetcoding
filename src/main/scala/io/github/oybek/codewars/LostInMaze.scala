package io.github.oybek.codewars

import scala.collection.mutable

object LostInMaze {

  private var lastMove: Option[Char] = None
  private var coord: (Int, Int) = (0, 0)
  private val was = new mutable.HashSet[(Int, Int)]
  was.add(coord)

  lazy val opposite: Char => Char = {
    case 'U' => 'D'
    case 'D' => 'U'
    case 'L' => 'R'
    case 'R' => 'L'
  }

  def move(dirs: List[Char]): Char = {
    val dirsA =
      dirs filterNot { x =>
        lastMove.contains(opposite(x)) || was.contains(coord.move(x))
      } sortBy {
        case 'L' => 1
        case 'U' => 2
        case 'R' => 3
        case 'D' => 4
      }
    val mov =
      dirsA
        .headOption
        .orElse(lastMove.map(opposite))
        .getOrElse(' ')
    lastMove = Some(mov)
    mov
  }

  implicit class Coord(val c: (Int, Int)) extends AnyVal {
    def move: Char => (Int, Int) = {
      case 'U' => (c._1, c._2 - 1)
      case 'D' => (c._1, c._2 + 1)
      case 'L' => (c._1 - 1, c._2)
      case 'R' => (c._1 + 1, c._2)
    }
  }
}
