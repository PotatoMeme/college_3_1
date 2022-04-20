package iducs.springboot.bootjpa.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestfulController {
    @GetMapping("/restful")
    public String getRestful(){ //data 를 바로 반환
        return "{\'attributeName\':\'attributeValue\'}";
    }
}
