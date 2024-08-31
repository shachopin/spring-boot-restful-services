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
    @Autowired
    private PermutationISolution permutationISolution; 

    @GetMapping("/dfs")
    public String bfsIndex() {
        return "<b><mark>Greetings from Spring Boot!</mark></b><hr>"
                + "<br>"
                + "POST /n-queen"
                + "<br>"
                + "POST /permutation-i"
                ;
    }

    @CrossOrigin
    @PostMapping("/n-queen")
    /* 
        requestbody is:
        {
           "queens": 4
        }

        fetch('https://8080-shachopin-springbootres-vj2jbqk7wsk.ws-us115.gitpod.io/n-queen', {method: 'post', headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
        }, body: JSON.stringify({
            "queens": [1,2,3]
            })}).then(res => res.json()).then(a => console.log(a));
    */
    public NQueenSolution.Output nqueen(@RequestBody NQueenSolution.Input input) {
        List<List<String>> out = nQueenSol.solveNQueens(input.getQueens());
        return new NQueenSolution.Output(out);
    }


    @CrossOrigin
    @PostMapping("/permutation-i")
    /* 
        requestbody is:
        {
           "nums": [1,2,3]
        }

        fetch('https://8080-shachopin-springbootres-vj2jbqk7wsk.ws-us115.gitpod.io/permutation-i', {method: 'post', headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
        }, body: JSON.stringify({
            "nums": [1,2,3]
            })}).then(res => res.json()).then(a => console.log(a));
    */
    public PermutationISolution.Output permutationi(@RequestBody PermutationISolution.Input input) {
        List<List<Integer>> out = permutationISolution.permute(input.getNums());
        return new PermutationISolution.Output(out);
    }
}



