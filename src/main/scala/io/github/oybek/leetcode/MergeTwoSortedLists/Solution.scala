package io.github.oybek.leetcode.MergeTwoSortedLists

class ListNode(_x: Int = 0, _next: ListNode = null) {
 var next: ListNode = _next
 var x: Int = _x
}

object Solution {
  def mergeTwoLists(l1: ListNode, l2: ListNode): ListNode = {
    if (l1 == null) l2
    else if (l2 == null) l1
    else {
      if (l1.x < l2.x) mergeTwoLists_(new ListNode(l1.x), l1.next, l2)
      else mergeTwoLists_(new ListNode(l2.x), l1, l2.next)
    }
  }

  def mergeTwoLists_(l: ListNode, l1: ListNode, l2: ListNode): ListNode = {
    if (l1 == null) {
      l.next = l2
      l
    } else if (l2 == null) {
      l.next = l1
      l
    } else {
      if (l1.x < l2.x) {
        l.next = new ListNode(l1.x)
        mergeTwoLists_(l.next, l1.next, l2)
      } else {
        l.next = new ListNode(l2.x)
        mergeTwoLists_(l.next, l1, l2.next)
      }
    }
  }
}
