package solutions.topoSort;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TopoSortController
{

    @GetMapping("/topoSort")
    public String topoSortIndex() {
        return "<b><mark>Greetings from Spring Boot!</mark></b><hr>"
                + "<br>"
                + "GET /course-schedule"
                + "<br>"
                ;
    }

    @GetMapping("/course-schedule")
    /* requestbody is:
        {
           "numCourses": 4,
           "prerequisites": [[1,0],[2,0],[3,1],[3,2]]
        }
    */
    public CourseScheduleSolution.Output courseOrder(@RequestBody CourseScheduleSolution.Input input) {
        CourseScheduleSolution sol = new CourseScheduleSolution();
        int[] out = sol.findOrder(input.getNumCourses(), input.getPrerequisites());
        return new CourseScheduleSolution.Output(out);
    }
}



