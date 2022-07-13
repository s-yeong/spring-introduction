package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    // localhost:8080요청이 오면 mapping됨
    public String home(){
        return "home";
        // home.html 호출
    }

}
