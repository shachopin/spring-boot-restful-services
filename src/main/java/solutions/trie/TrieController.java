package solutions.trie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.*;

@RestController
public class TrieController
{   @Autowired
    private AutoCompleteSolution autoCompleteSolution;
    private TrieNode root = new TrieNode();


    @GetMapping("/trie")
    public String trieIndex() {
        return "<b><mark>Greetings from Spring Boot!</mark></b><hr>"
                + "<br>"
                + "GET /autoComplete"
                + "<br>"
                + "GET /buildTrie"
                ;
    }

    @CrossOrigin
    @GetMapping("/autoComplete")
    /* 
       in browser directly type url, https://8080-shachopin-springbootres-iv0esdgne83.ws-us118.gitpod.io/autoComplete?prefix=dawei
    */
    public Set<String> autoComplete(@RequestParam String prefix) {
        return autoCompleteSolution.findWords(prefix, root);
    }

    @CrossOrigin
    @GetMapping("/buildTrie")
    /* 
    in browser directly type url, https://8080-shachopin-springbootres-iv0esdgne83.ws-us118.gitpod.io/buildTrie?words=apple,banana,cherry,applejuice,action,apple
    it will save to the same session for future findWords requests
    adding duplicate won't matter either
    until you delete all words by directly typing url https://8080-shachopin-springbootres-iv0esdgne83.ws-us118.gitpod.io/buildTrie?clearAll=true
    */
    public String buildTrie(@RequestParam(value = "words", required = false) String[] words, @RequestParam(value = "clearAll", required = false) boolean clearAll){
        if (words != null) {
            //List<String> listOfWords = Arrays.asList(words);
            autoCompleteSolution.buildTrie(words, root);
            return "Words upload success!";
        }
        
        if (clearAll) {
            root = new TrieNode();
            return "All words deleted";
        }

        return "No words given!";
        
    }
    
    //example
    //To handle an array as a request parameter in Spring Boot, the @RequestParam annotation is used in the controller method. It binds the request parameter to a method parameter. When dealing with arrays, the method parameter can be declared as an array or a list.
    // @GetMapping("/optional")
    // public String handleOptionalArray(@RequestParam(value = "names", required = false) String[] names) {
    //     if (names != null) {
    //         return "Received names: " + Arrays.toString(names);
    //     } else {
    //         return "No names received";
    //     }
    // }

    // @GetMapping("/array")
    // public String handleArrayRequest(@RequestParam("names") String[] names) {
    //     return "Received names array: " + Arrays.toString(names);
    // }

    //  @GetMapping("/list")
    // public String handleListRequest(@RequestParam("ids") List<Integer> ids) {
    //     return "Received ids list: " + ids.toString();
    // }

    // The client can send the array data in the request using the same parameter name multiple times or with comma separated values:

    // // Multiple parameters with same name
    // GET /array?names=John&names=Jane&names=Peter

    // // Comma-separated values
    // GET /list?ids=1,2,3
}

/*
 * https://www.reddit.com/r/learnjava/comments/tqaak5/im_confused_about_how_does_a_spring_boot_web/?rdt=51445
 * regarding spring boot how to share variable between requests, spring boot by default is multi-thread but singleton beans meaning all 1 instance for every bean, therefore sharing variable is BY DEFAULT! You should figure out how NOT to do that, therefore let bean scope to be request scope
 * 
.....I'm confused how concurrency is achieved in this situation if at all,

Your application server (eg. Tomcat) will retain a thread pool from which to serve requests. Whenever a request comes in, a thread gets allocated to that request from that thread pool, and it's used for the duration of the request. When the request is finished, the thread is returned to the pool.

This thread pool will scale up or down as necessary depending on load, and there's almost always various configuration parameters you can tweak in terms of the minimum / maximum / minimum spare etc. threads available to process new requests as they come in.

...I've never had the need to learn multithreading when working with Spring Boot.

You can usually "get away" with not knowing about it, your stack (and therefore local variables) are all per-thread, so these won't cause issues. There's definitely situations where it can cause issues though, so I'd at least recommend reading up on the basics of concurrency.

I wanted to add some additional points that might be helpful, although not directly related to your question.

Spring Beans default to Singleton scope, which means that there will only ever be one instance of them while your application is running. This means that all of your Controllers, Services, Repositories, etc code will be shared by multiple threads, which means they will be shared by multiple user requests running at the same time.

It's important to understand this point and it's implications. You should be designing all of these components to be stateless. That means you don't want to store user data in variables that will be shared between different request threads, otherwise you might end up leaking data between users. User state should only exist in parameters, and inside functions.

It's totally fine for your Repository class to store state about the database url, username, and password. These values are not user-specific. But you shouldn't have a 'username' field inside your controller, because each request will likely be from a different user.

If you need to store user data between requests you either need to rely on an external system (like a database) or start learning about the other scopes provided by Spring. (https://www.baeldung.com/spring-bean-scopes)

Aside from what others said; it's pretty important to know this because mistakes with concurrency (such as keeping state in a singleton component) are common errors that can be very hard to debug.

*/

