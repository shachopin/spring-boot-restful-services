package solutions.bfs;
import java.util.*;

public class NumberOfIslandsSolution {
    int[] dx = new int[]{0, 1, -1, 0};
    int[] dy = new int[]{1, 0, 0, -1};
    /**
     * @param grid: a boolean 2D matrix
     * @return: an integer
     */
    public int numIslands(int[][] grid) {
        // write your code here

        if (grid.length == 0) {
            return 0;
        }
        if (grid[0].length == 0) {
        	return 0;
        }

        int numOfIslands = 0;
        for (int i = 0; i < grid.length; i++) {
        	for (int j = 0; j < grid[0].length; j++) {
        		if (grid[i][j] == 1) {
        			numOfIslands++;
        			bfs(grid, i, j);
        		}
        	}
        }

        return numOfIslands;
    }

    private void bfs(int[][] grid, int x, int y) {
    	Queue<Point> queue = new LinkedList<>();
    	queue.offer(new Point(x, y));
    	grid[x][y] = 0;

    	while (!queue.isEmpty()) {
    		Point cur = queue.poll();
    		for (int i = 0; i < 4; i++) {
    			int newX = cur.x + dx[i];
    			int newY = cur.y + dy[i];
    			if (!isValid(newX, newY, grid)) {
    				continue;
    			}
    			if (grid[newX][newY] == 0) {
    				continue;
    			}

    			queue.offer(new Point(newX, newY));
    			grid[newX][newY] = 0;
    		}
    	}
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
        private int numOfIslands;

        public Output(int numOfIslands) {
            this.numOfIslands = numOfIslands;
        }

        public int getNumOfIslands() {
            return numOfIslands;
        }
    }
}