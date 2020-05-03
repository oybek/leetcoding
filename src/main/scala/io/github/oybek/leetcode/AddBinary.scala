package io.github.oybek.leetcode

object AddBinary {
  def addBinary(a: String, b: String): String =
    a.reverse.zipAll(b.reverse, '0', '0').foldLeft(List.empty[Char] -> false) {
      case ((s, false), ('0', '0')) => ('0'::s) -> false
      case ((s, true), ('0', '0')) => ('1'::s) -> false

      case ((s, false), ('0', '1')) => ('1'::s) -> false
      case ((s, true), ('0', '1')) => ('0'::s) -> true

      case ((s, false), ('1', '0')) => ('1'::s) -> false
      case ((s, true), ('1', '0')) => ('0'::s) -> true

      case ((s, false), ('1', '1')) => ('0'::s) -> true
      case ((s, true), ('1', '1')) => ('1'::s) -> true
    } match {
      case (s, false) => s.mkString
      case (s, true) => ('1'::s).mkString
    }
}
