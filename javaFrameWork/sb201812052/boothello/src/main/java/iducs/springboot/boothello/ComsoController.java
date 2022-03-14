package iducs.springboot.boothello;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ComsoController {
    @GetMapping("/")// get 요청과 메소드를 메핑시켜줌
    public String home(){
        return "<h1>String Boot Hello</h1>";
    }
    @GetMapping("/sample")
    public String sample(){
        return "String Boot Hello";
    }
}
