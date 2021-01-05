package io.github.oybek.leetcode.CheckArrayFormationThroughConcatenation

object Solution {
  def canFormArray(arr: Array[Int], pieces: Array[Array[Int]]): Boolean =
    pieces.forall(arr.containsSlice(_))
}
