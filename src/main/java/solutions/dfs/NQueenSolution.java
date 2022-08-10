package solutions.dfs;
import java.util.*;

public class NQueenSolution {
    /**
     * @param n: The number of queens
     * @return: All distinct solutions
     *          we will sort your return value in output
     */
    public List<List<String>> solveNQueens(int n) {
        // write your code here
        boolean[] visited = new boolean[n];
        List<List<String>> res = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        dfs(n, res, temp, visited);
        return res;
    }

    private void dfs(int n, List<List<String>> res, List<Integer> temp, boolean[] visited) {
        if (temp.size() == n) {
            //res.add
            res.add(converToBoard(temp, n));
            return;
        }
        for (int i = 0; i < n; i++) {
            if (canAttack(temp, i, visited)) {
                continue;
            }
            temp.add(i);
            visited[i] = true;
            dfs(n, res, temp, visited);
            visited[i] = false;
            temp.remove(temp.size() - 1);
        }
    }

    private boolean canAttack(List<Integer> temp, int i, boolean[] visited) {
        if (visited[i]) {
            return true;
        }
        int newRow = temp.size();
        for (int row = 0; row < temp.size(); row++) {
            if (Math.abs(newRow - row) == Math.abs(i - temp.get(row))) {
                return true;
            }
        }

        return false;
    }

    private List<String> converToBoard(List<Integer> temp, int n) {
        List<String> board = new ArrayList<>();
        StringBuilder strB = new StringBuilder();
        for (int i = 0; i < n; i++) {
            strB.append(".");
        }
        for (int pos : temp) {
            strB.setCharAt(pos, 'Q');
            board.add(strB.toString());
            strB.setCharAt(pos, '.');
        }

        return board;
    }


    static class Input {
        private int queens;

        public int getQueens() {
            return queens;
        }
    }

    static class Output {
        private List<List<String>> boards;

        public Output(List<List<String>> boards) {
            this.boards = boards;
        }

        public List<List<String>> getBoards() {
            return boards;
        }
    }
}