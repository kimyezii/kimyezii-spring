package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")    //1.첫 페이지 home.html호출
    public String home() {
        return "home";
    }
}
