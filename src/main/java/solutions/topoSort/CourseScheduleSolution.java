package solutions.topoSort;
import java.util.*;

public class CourseScheduleSolution {
    /*
     * @param numCourses: a total of n courses
     * @param prerequisites: a list of prerequisite pairs
     * @return: true if can finish all courses or false
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // write your code here
        List<Integer>[] graph = new List[numCourses];
        int[] indegree = new int[numCourses];
        buildGraphAndIndegree(graph, indegree, numCourses, prerequisites);
        return bfs(indegree, graph, numCourses);
    }

    private void buildGraphAndIndegree(List<Integer>[] graph, int[] indegree, int numCourses, int[][] prerequisites) {
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] prereq : prerequisites) {
            int source = prereq[1];
            int target = prereq[0];
            graph[source].add(target);
            indegree[target]++;
        }
    }

    private int[] bfs(int[] indegree, List<Integer>[] graph, int numCourses) {
        Queue<Integer> queue = new LinkedList<>();
        //List<Integer> res = new ArrayList<>();
        int[] ans = new int[numCourses];
        int count = 0;

        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            ans[count] = cur;
            count++;

            for (int next : graph[cur]) {
                indegree[next]--;
                if (indegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }

        if (count == numCourses) {
            return ans;
        }
        return new int[0]; //meaning cannot finish
    }

    static class Input {
        private int numCourses;
        private int[][] prerequisites;

        public int getNumCourses() {
            return numCourses;
        }

        public int[][] getPrerequisites() {
            return prerequisites;
        }
    }

    static class Output {
         private int[] courseOrder;

        public Output(int[] courseOrder) {
            this.courseOrder = courseOrder;
        }

        public int[] getCourseOrder() {
            return courseOrder;
        }
    }

}