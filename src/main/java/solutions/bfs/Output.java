package solutions.bfs;

import com.fasterxml.jackson.annotation.JsonFilter;
//dynamic filtering enabled, currently this is used in /filterTest route
@JsonFilter("SomeBeanFilter")
public class Output {
    //private boolean[][] grid;
    private String outputStr;
    private int outputNum;

    public Output(String outputStr) {
        this.outputStr = outputStr;
        //this.grid = grid;
    }

    public Output(int outputNum) {
        this.outputNum = outputNum;
    }

    public String getOutputStr() {
        return outputStr;
    }

    public int getOutputNum() {
        return outputNum;
    }

    // public boolean[][] getGrid() {
    //     return grid;
    // }

}