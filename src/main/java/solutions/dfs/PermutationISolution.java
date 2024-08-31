package solutions.dfs;
import java.util.*;
import org.springframework.stereotype.Component;

@Component
public class PermutationISolution {
    
    public List<List<Integer>> permute(int[] nums) {
        // write your code here
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null) {
            return res;
        }
        
        boolean[] visited = new boolean[nums.length];
        List<Integer> temp = new ArrayList<>();
        
        dfs(nums, res, temp, visited);
        
        return res;
        
    }
    
    private void dfs(int[] nums, List<List<Integer>> res, List<Integer> temp, boolean[] visited) {
        if (temp.size() == nums.length) {
            res.add(new ArrayList<Integer>(temp));
            return;
        }
        
        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) {
                continue;
            }
            temp.add(nums[i]); //mutable临时结果集(回溯) 
            visited[i] = true; //宽度剪枝依据的全局变量，for parent child dedup要回溯
            dfs(nums, res, temp, visited);
            temp.remove(temp.size() - 1);
            visited[i] = false;
        }
    }

    static class Input {
        private int[] nums;

        public int[] getNums() {
            return nums;
        }
    }

    static class Output {
        private List<List<Integer>> permutations;

        public Output(List<List<Integer>> permutations) {
            this.permutations = permutations;
        }

        public List<List<Integer>> getPermutations() {
            return permutations;
        }
    }
}
