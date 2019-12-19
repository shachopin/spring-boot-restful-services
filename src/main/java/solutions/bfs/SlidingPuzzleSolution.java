package solutions.bfs;
import java.util.*;

public class SlidingPuzzleSolution {
    public int minMoveStep(int[][] init_state, int[][] final_state) {
        // # write your code here
        //1D
        //shortest path
        //single source
        //singele target
        //need to create your own datastructure for dedup
        //3 level looping

        int[] dx = new int[]{0, 1, -1, 0};
        int[] dy = new int[]{1, 0, 0, -1};

        Queue<String> queue = new LinkedList<>();
        Set<String> set = new HashSet<>();
        String initial = convertToString(init_state);
        queue.offer(initial);
        set.add(initial);
        int length = 0;

        String end = convertToString(final_state);

        while (!queue.isEmpty()) {
            length++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String cur = queue.poll();
                for (int j = 0; j < 4; j++) {
                    char[] chars = cur.toCharArray();
                    int indexOfZero = cur.indexOf('0'); //str.indexOf(char); array.indexOf not exist
                    int x = indexOfZero / 3;
                    int y = indexOfZero % 3;
                    int newX = x + dx[j];
                    int newY = y + dy[j];

                    if (!isValid(newX, newY)) {
                        continue;
                    }

                    int newIndex = newX * 3 + newY;

                    char tmp = chars[indexOfZero];
                    chars[indexOfZero] = chars[newIndex];
                    chars[newIndex] = tmp;
                    String nextStr = String.valueOf(chars);

                    if (set.contains(nextStr)) {
                        continue;
                    }
                    if (end.equals(nextStr)) {
                        return length;
                    }

                    queue.offer(nextStr);
                    set.add(nextStr);
                }
            }
        }

        return -1;

    }

    private boolean isValid(int x, int y) {
        if (x < 0 || x >= 3 || y < 0 || y >= 3) {
            return false;
        }

        return true;
    }

    private String convertToString(int[][] state) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                sb.append(state[i][j]);
            }
        }
        return sb.toString();
    }

    static class Input {
        private int[][] initState;
        private int[][] finalState;

        public int[][] getInitState() {
            return initState;
        }

        public int[][] getFinalState() {
            return finalState;
        }
    }

    static class Output {
         private int minSwaps;

        public Output(int minSwaps) {
            this.minSwaps = minSwaps;
        }

        public int getMinSwaps() {
            return minSwaps;
        }
    }
}