package io.github.oybek.codewars

import org.scalatest.{FlatSpec, Matchers}
import scala.jdk.CollectionConverters._

class SortBTreeByLevelsSpec extends FlatSpec with Matchers {

  "solution" should "pass tests" in {
    SortBTreeByLevels.treeByLevels(null).asScala.toList should be (List())
    SortBTreeByLevels.treeByLevels(
      new Node(
        new Node(null, new Node(null, null, 4), 2),
        new Node(new Node(null, null, 5), new Node(null, null, 6), 3),
        1
      )).asScala.toList should be (List(1,2,3,4,5,6))
  }
}
