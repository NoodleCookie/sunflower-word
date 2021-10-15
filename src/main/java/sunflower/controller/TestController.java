package sunflower.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sunflower.configuration.UserContext;

@RestController
public class TestController {

    @GetMapping("/user")
    public String getUsername(){
        return UserContext.getUser();
    }
}
