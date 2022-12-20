package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    // localhost:8080요청이 오면 mapping됨
    public String home(){
        return "home";
        // home.html 호출
    }

    // index.html이 있지만, 정적 페이지를 보기 전에 Controller를 먼저 보기 때문에 home.html 작동

}
