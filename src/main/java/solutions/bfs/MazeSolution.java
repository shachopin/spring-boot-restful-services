package solutions.bfs;
import java.util.*;
/*

// MySolution: 787. The Maze on lintcode
// 非对称的BFS环
// 有一个direction可以走很久
// 而且direction可以只走一步
// 四个方向走的步数不一样

6 questions
  2D
  connected component
  1 source
  1 destination
  2 loops
  create your dedup data structure
*/
public class MazeSolution {

    int[] dx = new int[]{0, 1, -1, 0};
    int[] dy = new int[]{1, 0, 0, -1};
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        // write your code here
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(start[0], start[1]));
        int m = maze.length;
        int n = maze[0].length;
        boolean[][] visited = new boolean[m][n];
        visited[start[0]][start[1]] = true;

        //create another 2D array as dedup basis
        //is important
        //不要mutate重用题目的maze[][]做dedup, 因为1表示题目原本就给的障碍，你需要boolean[][] visited 判断是不是访问过，不能用1表示，否则会走到不应该走到的点

        while (!queue.isEmpty()) {
            Point cur = queue.poll();
            for (int i = 0; i < 4; i++) {
                int newX = cur.x + dx[i];
                int newY = cur.y + dy[i];
                while (isValid(newX, newY, maze) && maze[newX][newY] == 0) {
                    newX += dx[i];
                    newY += dy[i];
                }
                newX -= dx[i];
                newY -= dy[i];
                //四个方向走的步数不一样
                //下面还是模版

                if (visited[newX][newY]) {
                    continue;
                }

                if (newX == destination[0] && newY == destination[1]) {
                    return true;
                }

                queue.offer(new Point(newX, newY));
                visited[newX][newY] = true;
            }
        }

        return false;
    }
    private boolean isValid(int x, int y, int[][] maze) {
        if (x < 0 || x >= maze.length || y < 0 || y >= maze[0].length) {
            return false;
        }
        return true;
    }


    static class Input {
        private int[][] maze;
        private int[] start;
        private int[] destination;

        public int[][] getMaze() {
            return maze;
        }

        public int[] getStart() {
            return start;
        }

        public int[] getDestination() {
            return destination;
        }

    }

    static class Output {
        private boolean hasPath;

        public Output(boolean hasPath) {
            this.hasPath = hasPath;
        }

        public boolean getHasPath() {
            return hasPath;
        }
    }
}