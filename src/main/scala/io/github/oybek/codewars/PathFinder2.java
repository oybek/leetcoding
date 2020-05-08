package io.github.oybek.codewars;

import java.util.LinkedList;
import java.util.Queue;

public class PathFinder2 {

    public static int pathFinder(String m) {
        int[][] maze = createMaze(m);

        Queue<int[]> Q = new LinkedList<>();
        Q.add(new int []{0, 0, 0});
        maze[0][0] = 0;
        while (!Q.isEmpty()) {
            int[] p = Q.poll();
            int x = p[0];
            int y = p[1];
            int z = p[2];

            if (x == maze.length-1 && y == maze[0].length-1) break;
            if (canGo(maze, x-1, y)) {
                Q.add(new int[] {x-1, y, z+1});
                maze[x-1][y] = z+1;
            }
            if (canGo(maze, x+1, y)) {
                Q.add(new int[] {x+1, y, z+1});
                maze[x+1][y] = z+1;
            }
            if (canGo(maze, x, y-1)) {
                Q.add(new int[] {x, y-1, z+1});
                maze[x][y-1] = z+1;
            }
            if (canGo(maze, x, y+1)) {
                Q.add(new int[] {x, y+1, z+1});
                maze[x][y+1] = z+1;
            }
        }
        return maze[maze.length-1][maze[0].length-1];
    }

    private static boolean canGo(int[][] maze, int x, int y) {
        return x >= 0 && y >= 0 && x < maze.length && y < maze[0].length && maze[x][y] == -1;
    }

    private static int[][] createMaze(String m) {
        String[] maze1 = m.split("\n");
        int[][] maze = new int[maze1.length][maze1[0].length()];
        for (int i = 0; i < maze.length; ++i) {
            for (int j = 0; j < maze1[i].length(); ++j) {
                maze[i][j] = maze1[i].charAt(j) == 'W' ? -2 : -1;
            }
        }
        return maze;
    }
}
