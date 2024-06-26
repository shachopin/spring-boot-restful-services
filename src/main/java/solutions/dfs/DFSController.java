package solutions.dfs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
@RestController
public class DFSController
{   @Autowired
    private NQueenSolution nQueenSol;

    @GetMapping("/dfs")
    public String bfsIndex() {
        return "<b><mark>Greetings from Spring Boot!</mark></b><hr>"
                + "<br>"
                + "POST /n-queen"
                ;
    }

    @CrossOrigin
    @PostMapping("/n-queen")
    /* 
        requestbody is:
        {
           "queens": 4
        }
    */
    public NQueenSolution.Output nqueen(@RequestBody NQueenSolution.Input input) {
        List<List<String>> out = nQueenSol.solveNQueens(input.getQueens());
        return new NQueenSolution.Output(out);
    }
    
}



