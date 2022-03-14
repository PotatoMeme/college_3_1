package iducs.spring.webmvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ComsoController {
    @GetMapping("/sample")
    public String sample(){return "/WEB_INF/sample.jsp";}
}
