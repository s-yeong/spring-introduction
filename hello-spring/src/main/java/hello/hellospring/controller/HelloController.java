package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    // 웹 어플리케이션에서 /hello라고 들어오면, 이 method를 호출해준다.
    public String hello(Model model) {
        model.addAttribute("data", "spring~!");
        return "hello"; }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name",name);
        return "hello-template";
    }

    // API 방식
    @GetMapping("hello-string")
    @ResponseBody
    // ResponeBody의 의미는 HTTP에서 Body에 이 데이터를 직접 넣어주겠다. (문자 내용 직접 반환)
    public String helloString(@RequestParam("name") String name){
        return "hello " + name;
        // "hello spring" 이 문자가 요청한 클리언트에게 그대로 데려감
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;   // 객체를 넘김 (객체 -> JSON 변환)
    }

    static class Hello {
        // Hello 객체 만듬, static으로 만들면 HelloController 안에 이 것을 또 쓸 수있음
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }



}
