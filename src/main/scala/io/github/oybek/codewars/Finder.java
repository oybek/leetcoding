package io.github.oybek.codewars;

import scala.concurrent.impl.FutureConvertersImpl;

import java.util.*;
import java.awt.Point;
import java.util.stream.Stream;

public class Finder {

    static class Coord {
        int cost, x, y;
        List<String> path;

        public Coord(int cost, int x, int y, List<String> path) {
            this.cost = cost;
            this.x = x;
            this.y = y;
            this.path = path;
        }

        public Coord copy(int cost, List<String> path) {
            return Coord.of(cost, x, y, path);
        }

        public static Coord of(int cost, int x, int y, List<String> path) {
            return new Coord(cost, x, y, path);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Coord coord = (Coord) o;
            return x == coord.x && y == coord.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    static List<String> cheapestPath(int[][] t, Point start, Point finish) {
        boolean[][] done = new boolean[t.length][t[0].length];
        int[][] dist = new int[t.length][t[0].length];

        for (boolean[] row: done) Arrays.fill(row, false);
        for (int[] row: dist) Arrays.fill(row, Integer.MAX_VALUE);

        PriorityQueue<Coord> PQ = new PriorityQueue<>(Comparator.comparingInt(o -> o.cost));
        PQ.add(Coord.of(t[start.x][start.y], start.x, start.y, Collections.emptyList()));

        while (!PQ.isEmpty()) {
            Coord coord = PQ.poll();

            if (coord.x == finish.x && coord.y == finish.y)
                return coord.path;

            if (coord.x-1 >= 0 && done[coord.x-1][coord.y])
        }

        return new ArrayList<>();
    }
}
