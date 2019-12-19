package solutions.bfs;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SolutionsController
{
    @GetMapping("/")
    public String index() {
        return "<b><mark>Greetings from Spring Boot!</mark></b><hr>"
                + "<br>"
                + "GET /filtering-test"
                + "<br>"
                + "GET /word-ladder"
                + "<br>"
                + "GET /zombie-matrix"
                + "<br>"
                + "GET /serialize-and-deserialize-binary-tree"
                + "<br>"
                + "GET /number-of-islands"
                + "<br>"
                + "GET /sliding-puzzle"
                ;
    }


    //Dynamic Field Filtering: outputStr - yes show; outputNum - no show
	@GetMapping("/filtering-test")
	public MappingJacksonValue retrieveSomeBean() {
        Output someBean = new Output("I want to only show string field");
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("outputStr");
		//if say SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("outputStr", "outputNum");
        //will show {"outputStr":"I want to only show string field","outputNum":0}
        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
		MappingJacksonValue mapping = new MappingJacksonValue(someBean);
		mapping.setFilters(filters);
		return mapping;
	}

    @GetMapping("/word-ladder")
    /* requestbody is:
        {
            "start": "hit",
            "end": "cog",
            "dict": ["hot","dot","dog","lot","log"]
        }
    */
    public WordLadderSolution.Output worldLadder(@RequestBody WordLadderSolution.Input input) {
        WordLadderSolution sol = new WordLadderSolution();
        int out = sol.ladderLength(input.getStart(), input.getEnd(), input.getDict());
        return new WordLadderSolution.Output(out);
    }

    @GetMapping("/zombie-matrix")
    /* requestbody is:
        {
           "grid": [[0,1,2,0,0],
                    [1,0,0,2,1],
                    [0,1,0,0,0]]
        }
    */
    public ZombieMatrixSolution.Output zombieMatrix(@RequestBody ZombieMatrixSolution.Input input) {
        ZombieMatrixSolution sol = new ZombieMatrixSolution();
        int out = sol.zombie(input.getGrid());
        return new ZombieMatrixSolution.Output(out);
    }

    @GetMapping({ "/serialize-deserialize-binary-tree" })
    /* requestbody is:
        {
           "data": "{3,9,20,#,#,15,7}"
        }
    */
    public SerializeAndDeserializeBinaryTreeSolution.Output serializeAndDeserializeBinaryTree(@RequestBody SerializeAndDeserializeBinaryTreeSolution.Input input) {
        SerializeAndDeserializeBinaryTreeSolution sol = new SerializeAndDeserializeBinaryTreeSolution();
        TreeNode root = sol.deserialize(input.getData());
        String result = sol.serialize(root);
        return new SerializeAndDeserializeBinaryTreeSolution.Output(result);
    }

    @GetMapping("/number-of-islands")
    /* requestbody is:
        {
            "grid": [[0,1,0,0,0],
                    [1,1,0,1,1],
                    [0,1,0,0,0]]
        }
    */
    public NumberOfIslandsSolution.Output numIslands(@RequestBody NumberOfIslandsSolution.Input input) {
        NumberOfIslandsSolution sol = new NumberOfIslandsSolution();
        int out = sol.numIslands(input.getGrid());
        return new NumberOfIslandsSolution.Output(out);
    }

    @GetMapping("/sliding-puzzle")
    /* requestbody is:
        {
            "initState":    [
                            [2,8,3],
                            [1,0,4],
                            [7,6,5]
                            ],
            "finalState":   [
                            [1,2,3],
                            [8,0,4],
                            [7,6,5]
                            ]
        }
    */
    public SlidingPuzzleSolution.Output numIslands(@RequestBody SlidingPuzzleSolution.Input input) {
        SlidingPuzzleSolution sol = new SlidingPuzzleSolution();
        int out = sol.minMoveStep(input.getInitState(), input.getFinalState());
        return new SlidingPuzzleSolution.Output(out);
    }
}
