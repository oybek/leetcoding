package io.github.oybek.codewars

object EnoughIsEnough {
  def deleteNth(elements: List[Int], maxOccurrences: Int): List[Int] =
    elements.zipWithIndex
      .groupBy(_._1)
      .values
      .flatMap(_.take(maxOccurrences))
      .toList
      .sortBy(_._2)
      .map(_._1)
}
