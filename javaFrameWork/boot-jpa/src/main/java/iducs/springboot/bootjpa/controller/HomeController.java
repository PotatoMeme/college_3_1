package iducs.springboot.bootjpa.controller;

import iducs.springboot.bootjpa.domain.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {
    // MemoService 인터페이스로 부터 구현가능한 객체를 생성해서 주입
    // Spring Bean 객체로 구현하였기 때문에 Spring이 주입할 수 있음
    @GetMapping("")
    public String getHome(){
        return "index";
    }
}

