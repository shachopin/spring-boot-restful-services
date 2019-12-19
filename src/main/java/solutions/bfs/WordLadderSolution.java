package solutions.bfs;
import java.util.*;

public class WordLadderSolution {
    public int ladderLength(String start, String end, Set<String> dict) {
        // write your code here
        Queue<String> queue = new LinkedList<>();
        Map<String, Integer> map = new HashMap<>();
        dict.add(end);
        queue.offer(start);
        map.put(start, 1);

        while (!queue.isEmpty()) {
            String current = queue.poll();
            for (String next : getNextWords(current, dict)) {
                if (map.containsKey(next)) {
                    continue;
                }

                map.put(next, map.get(current) + 1);

                if (next.equals(end)) {
                    return map.get(next);
                }

                queue.offer(next);
            }
        }

        return -1;

    }

    private List<String> getNextWords(String cur, Set<String> dict) {
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder(cur);
        for (int i = 0; i < cur.length(); i++) {
            for (char ch = 'a'; ch <= 'z'; ch++) {
                if (cur.charAt(i) == ch) {
                    continue;
                }

                sb.setCharAt(i, ch);
                if (dict.contains(sb.toString())) {
                    res.add(sb.toString());
                }

            }
            sb.setCharAt(i, cur.charAt(i));
        }

        return res;
    }

    static class Input {
        private String start;
        private String end;
        private Set<String> dict;

        public String getStart() {
            return start;
        }

        public String getEnd() {
            return end;
        }

        public Set<String> getDict() {
            return dict;
        }
    }


    static class Output {
        private int minLength;

        public Output(int minLength) {
            this.minLength = minLength;
        }

        public int getMinLength() {
            return minLength;
        }
    }
}