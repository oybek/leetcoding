package io.github.oybek.leetcode.MergeTwoSortedLists

import io.github.oybek.leetcode.MergeTwoSortedLists.Solution
import org.scalatest.{FlatSpec, Matchers}

class SolutionSpec extends FlatSpec with Matchers {
  def ln(x: Int, next: ListNode): ListNode =
    new ListNode(x, next)

  def toList(l: ListNode): List[Int] =
    if (l == null) List.empty
    else l.x::toList(l.next)

  it should "pass example tests" in {
    toList(Solution.mergeTwoLists(
      ln(1, ln(2, ln(4, null))),
      ln(1, ln(3, ln(4, null)))
    )) should be (toList(ln(1, ln(1, ln(2, ln(3, ln(4, ln(4, null))))))))
  }
}
