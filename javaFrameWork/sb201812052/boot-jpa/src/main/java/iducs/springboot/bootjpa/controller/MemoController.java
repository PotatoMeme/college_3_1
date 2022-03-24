package iducs.springboot.bootjpa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemoController {
    @GetMapping("/tables")
    public String getTables(){
        return "tables";
    }
    @GetMapping("/")
    public String getIndex(){
        return "index";
    }
    @GetMapping("/charts")
    public String getCharts(){
        return "charts";
    }
}
