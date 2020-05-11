package io.github.oybek.codewars;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

public class SortBTreeByLevels {
    public static List<Integer> treeByLevels(Node node)
    {
        if (node == null) return new LinkedList<>();
        Queue<Node> queue = new LinkedBlockingQueue<>();
        List<Integer> res = new LinkedList<>();

        queue.add(node);
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            res.add(cur.value);

            if ( cur.left != null)  queue.add(cur.left);
            if (cur.right != null) queue.add(cur.right);
        }

        return res;
    }
}
