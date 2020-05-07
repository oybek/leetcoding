package io.github.oybek.codewars;

public class PathFinder1 {
    static boolean pathFinder(String m) {
        boolean[][] maze = createMaze(m);
        floodFill(maze, 0, 0);
        return maze[maze.length-1][maze[0].length-1];
    }

    private static void floodFill(boolean[][] maze, int x, int y) {
        maze[x][y] = true;
        if (canGo(maze, x-1, y)) floodFill(maze, x-1, y);
        if (canGo(maze, x+1, y)) floodFill(maze, x+1, y);
        if (canGo(maze, x, y-1)) floodFill(maze, x, y-1);
        if (canGo(maze, x, y+1)) floodFill(maze, x, y+1);
    }

    private static boolean canGo(boolean[][] maze, int x, int y) {
        return x >= 0 && y >= 0 && x < maze.length && y < maze[0].length && !maze[x][y];
    }

    private static boolean[][] createMaze(String m) {
        String[] maze1 = m.split("\n");
        boolean[][] maze = new boolean[maze1.length][maze1[0].length()];
        for (int i = 0; i < maze.length; ++i) {
            for (int j = 0; j < maze1[i].length(); ++j) {
                maze[i][j] = maze1[i].charAt(j) == 'W';
            }
        }
        return maze;
    }
}
