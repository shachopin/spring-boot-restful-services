package solutions.bfs;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BFSController
{
    
    @GetMapping("/bfs")
    public String bfsIndex() {
        return "<b><mark>Greetings from Spring Boot!</mark></b><hr>"
                + "<br>"
                + "GET /filtering-test"
                + "<br>"
                + "GET /word-ladder"
                + "<br>"
                + "POST /zombie-matrix"
                + "<br>"
                + "GET /serialize-and-deserialize-binary-tree"
                + "<br>"
                + "GET /number-of-islands"
                + "<br>"
                + "GET /sliding-puzzle"
                + "<br>"
                + "GET /maze"
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

    @CrossOrigin
    @PostMapping("/zombie-matrix")
    /* 
        requestbody is:
        {
           "grid": [[0,1,2,0,0],
                    [1,0,0,2,1],
                    [0,1,0,0,0]]
        }
    //注意本来上面是GetMapping,但是window.fetch的get不让传body），虽然postman可以，also没有crossOrigin也会fail,虽然postman里也可以
    fetch('https://8080-scarlet-goose-8dr9ngpg.ws-us17.gitpod.io/zombie-matrix', {method: 'post', headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    }, body: JSON.stringify({
           "grid": [[0,1,2,0,0],
                    [1,0,0,2,1],
                    [0,1,0,0,0]]
        })}).then(res => res.json()).then(a => console.log(a));
    Promise {<pending>}
    VM1036:8 {days: 2}

    here the workspace will shutdown after 30mins 

    deploy it to heroku to persist it
    https://devcenter.heroku.com/articles/deploying-spring-boot-apps-to-heroku

    deployed it to
    https://dawei-spring.herokuapp.com

    fetch('https://dawei-spring.herokuapp.com/zombie-matrix', {method: 'post', headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    }, body: JSON.stringify({
           "grid": [[0,1,2,0,0],
                    [1,0,0,2,1],
                    [0,1,0,0,0]]
        })}).then(res => res.json()).then(a => console.log(a));

    github is here /Users/daweidai/Downloads/spring-boot-restful-services on your own mac after using git clone https://github.com/shachopin/spring-boot-restful-services.git
   
    in order for gitpod to push to your github, give it permission

    From heroku, you enabled the auto deployment from github: changes to github -> heroku

    To collaborate with another person on another machine having gitpod account logged in though,
    no need to create team or having that person download your project
    just share your workspace, and copy the link to give that person the access to your live running workspace, 
    he can commit stuff under your name
    
    https://www.gitpod.io/docs/sharing-and-collaboration#sharing-snapshots
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


    @GetMapping("/maze")
    /* requestbody is:
        {
            "maze": [
                        [0,0,1,0,0],
                        [0,0,0,0,0],
                        [0,0,0,1,0],
                        [1,1,0,1,1],
                        [0,0,0,0,0]
                    ],
            "start" : [0,4],
            "destination": [3,2]
        }

        {

            "maze": [
                        [0,0,1,0,0],
                        [0,0,0,0,0],
                        [0,0,0,1,0],
                        [1,1,0,1,1],
                        [0,0,0,0,0]
                    ],
            "start": [0,4],
            "destination": [4,4]
        }
    */
    public MazeSolution.Output mazeHasPath(@RequestBody MazeSolution.Input input) {
        MazeSolution sol = new MazeSolution();
        boolean out = sol.hasPath(input.getMaze(), input.getStart(), input.getDestination());
        return new MazeSolution.Output(out);
    }
}



