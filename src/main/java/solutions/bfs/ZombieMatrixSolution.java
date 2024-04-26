package solutions.bfs;
import java.util.*;

public class ZombieMatrixSolution {
    /**
     * @param grid: a 2D integer grid
     * @return: an integer
     */
    public int zombie(int[][] grid) {
        // write your code here
        if (grid.length == 0) {
            return 0;
        }
        int ZOMBIE = 1;
        int PERSON = 0;
        //int WALL = 2;
        int[] dx = new int[]{0, 1, -1, 0};
        int[] dy = new int[]{1, 0, 0, -1};

        int personCount = 0;
        Queue<Point> queue = new LinkedList<>();

        int m = grid.length;
        int n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == ZOMBIE) {
                    queue.offer(new Point(i, j));
                }
                if (grid[i][j] == PERSON) {
                    personCount++;
                }
            }
        }

        if (personCount == 0) {
            return 0;
        }

        int days = 0;
        while (!queue.isEmpty()) {
            days++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Point cur = queue.poll();
                for (int dir = 0; dir < 4; dir++) {
                    int newX = cur.x + dx[dir];
                    int newY = cur.y + dy[dir];
                    if (!isValid(newX, newY, grid)) {
                        continue;
                    }
                    if (grid[newX][newY] != PERSON) {
                        continue;
                    }//as long as not PERSON, skip

                    personCount--;
                    if (personCount == 0) {
                        return days;
                    }

                    //if reach here, then must be PERSON
                    queue.offer(new Point(newX, newY));
                    grid[newX][newY] = ZOMBIE;

                }
            }
        }
        return -1;
    }

    private boolean isValid(int x, int y, int[][] grid) {
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length) {
            return false;
        }
        return true;
    }

    static class Input {
        private int[][] grid;
        public int[][] getGrid() {
            return grid;
        }
    }


    static class Output {
        private int days;

        public Output(int days) {
            this.days = days;
        }

        public int getDays() {
            return days;
        }
    }
}