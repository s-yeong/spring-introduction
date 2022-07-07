package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello")
    // 웹 어플리케이션에서 /hello라고 들어오면, 이 method를 호출해준다.
    public String hello(Model model) {
        model.addAttribute("data", "spring~!");
        return "hello"; }

}
