package iducs.springboot.kshboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
    // MemoService 인터페이스로 부터 구현가능한 객체를 생성해서 주입
    // Spring Bean 객체로 구현하였기 때문에 Spring이 주입할 수 있음
    @GetMapping("")
    public String getHome(){
        return "index";
    }
    @GetMapping("members/test")
    public String getTest(){
        return "members/simple";
    }
}

