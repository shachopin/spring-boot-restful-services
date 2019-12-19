package solutions.bfs;
import java.util.*;

class TreeNode {
    public int val;
    public TreeNode left, right;
    public TreeNode(int val) {
        this.val = val;
        this.left = this.right = null;
    }
}

public class SerializeAndDeserializeBinaryTreeSolution {
    /**
     * This method will be invoked first, you should design your own algorithm
     * to serialize a binary tree which denote by a root node to a string which
     * can be easily deserialized by your own "deserialize" method later.
     */
    public String serialize(TreeNode root) {
        // write your code here
        if (root == null) {
            return "{}";
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (cur != null) {
                sb.append(cur.val);
                queue.offer(cur.left);
                queue.offer(cur.right);
            } else {
                sb.append("#");
            }
            sb.append(",");
        }

        int index = sb.length() - 2;
        while (!Character.isDigit(sb.charAt(index))) {
            index--;
        }

        return sb.substring(0, index + 1) + "}";


    }

    /**
     * This method will be invoked second, the argument data is what exactly
     * you serialized at method "serialize", that means the data is not given by
     * system, it's given by your own serialize method. So the format of data is
     * designed by yourself, and deserialize it here as you serialize it in
     * "serialize" method.
     */
    public TreeNode deserialize(String data) {
        // write your code here
        if (data.equals("{}")) {
            return null;
        }

        String[] parts = data.substring(1, data.length() - 1).split(",");
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = new TreeNode(Integer.parseInt(parts[0]));
        queue.offer(root);
        for (int i = 1; i < parts.length; i++) {
            TreeNode cur = queue.poll();
            if (!"#".equals(parts[i])) {
                cur.left = new TreeNode(Integer.parseInt(parts[i]));
                queue.offer(cur.left);
            }
            i++;
            if (i >= parts.length) {
                break;
            }
            if (!"#".equals(parts[i])) {
                cur.right = new TreeNode(Integer.parseInt(parts[i]));
                queue.offer(cur.right);
            }

        }

        return root;

    }

    static class Input {
        private String data;

        public String getData() {
            return data;
        }

    }

    static class Output {
        private String result;

        public Output(String result) {
            this.result = result;
        }

        public String getResult() {
            return result;
        }
    }
}