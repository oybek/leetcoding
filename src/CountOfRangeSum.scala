
// https://leetcode.com/problems/count-of-range-sum/
object CountOfRangeSum extends App {

  def prefixSums(nums: Array[Int]): Array[Int] =
    nums.foldLeft(List.empty[Int]) {
      case (Nil, y) => List(y)
      case (x::xs, y) => (x+y)::x::xs
    }.toArray

  def countRangeSum(nums: Array[Int], lower: Int, upper: Int) = {
    // ...
  }
}
