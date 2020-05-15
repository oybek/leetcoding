package io.github.oybek.codewars;

import java.util.*;

public class TheAlpinist {
    static int pathFinder(String rawMaze) {
        int[][] maze = convert(rawMaze);
        int[][] dist = new int[maze.length][maze.length];
        boolean[][] visited = new boolean[maze.length][maze.length];
        PriorityQueue<Coord> pq = new PriorityQueue<>(Comparator.comparingInt(c -> dist[c.x][c.y]));

        for (int i = 0; i < maze.length; ++i) {
            for (int j = 0; j < maze.length; ++j) {
                dist[i][j] = (i == 0 && j == 0 ? 0 : 1000000009);
            }
        }

        pq.add(new Coord(0, 0));
        while (!pq.isEmpty()) {
            Coord c = pq.poll();
            visited[c.x][c.y] = true;

            List<Coord> neighbours = c.getNear(maze.length);
            for (Coord n: neighbours) {
                int d = dist[c.x][c.y] + Math.abs(maze[c.x][c.y] - maze[n.x][n.y]);
                if (!visited[n.x][n.y] && d < dist[n.x][n.y]) {
                    dist[n.x][n.y] = d;
                    pq.add(n);
                }
            }
        }
        return dist[maze.length-1][maze.length-1];
    }

    private static int[][] convert(String maze) {
        String[] mz = maze.split("\n");
        int[][] res = new int[mz.length][mz.length];
        for (int i = 0; i < mz.length; ++i)
            for (int j = 0; j < mz.length; ++j)
                res[i][j] =  mz[i].charAt(j)-'0';
        return res;
    }

    private static class Coord {
        private int x, y;
        public Coord(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Coord coord = (Coord) o;
            return x == coord.x && y == coord.y;
        }

        public List<Coord> getNear(int mazelen) {
            List<Coord> cs = new ArrayList<>();
            if (x-1 >= 0) cs.add(new Coord(x-1, y));
            if (y-1 >= 0) cs.add(new Coord(x, y-1));
            if (x+1 < mazelen) cs.add(new Coord(x+1, y));
            if (y+1 < mazelen) cs.add(new Coord(x, y+1));
            return cs;
        }

        @Override
        public int hashCode() { return Objects.hash(x, y); }
    }
}
