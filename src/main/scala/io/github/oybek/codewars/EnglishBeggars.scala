package io.github.oybek.codewars

object EnglishBeggars {

  def beggars(values: List[Int], n: Int): List[Int] =
    if (n == 0) {
      List.empty[Int]
    } else {
      values.foldLeft((List.fill(n)(0), 0)) {
        case ((bs, i), x) => (bs.updated(i, bs(i) + x), (i+1) % bs.length)
      }._1
    }
}
