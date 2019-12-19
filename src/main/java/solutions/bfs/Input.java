package solutions.bfs;
//current this class not being used,
//I have static inner input and output class inside each solution
import java.util.Set;

public class Input {
    private boolean[][] grid;
    private String sourceStr;
    private String targetStr;
    private Set<String> dict;
    // no need for constructor
    // public Input( String sourceStr, boolean[][] grid) {
    //     super();
    //     this.sourceStr = sourceStr;
    //     this.grid = grid;
    // }

    public boolean[][] getGrid() {
        return grid;
    }

    public String getSourceStr() {
        return sourceStr;
    }

    public String getTargetStr() {
        return targetStr;
    }

    public Set<String> getDict() {
        return dict;
    }



}