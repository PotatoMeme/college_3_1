package iducs.springboot.bootjpa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemoController {
    // index 맵핑
    @GetMapping("/")
    public String getIndex(){
        return "index";
    }
    @GetMapping("/index")
    public String getIndexs(){
        return "index";
    }
    // buttons 맵핑
    @GetMapping("/buttons")
    public String getButtons(){
        return "buttons";
    }
    // cards 맵핑
    @GetMapping("/cards")
    public String getCards(){
        return "cards";
    }
    // utilities-color 맵핑
    @GetMapping("/utilities-color")
    public String getUtilitiesColor(){
        return "utilities-color";
    }
    // utilities-border 맵핑
    @GetMapping("/utilities-border")
    public String getUtilitiesBorder(){
        return "utilities-border";
    }
    // utilities-animation 맵핑
    @GetMapping("/utilities-animation")
    public String getUtilitiesAnimation(){
        return "utilities-animation";
    }
    // utilities-other 맵핑
    @GetMapping("/utilities-other")
    public String getUtilitiesOther(){
        return "utilities-other";
    }
    // login 맵핑
    @GetMapping("/login")
    public String getLogin(){
        return "login";
    }
    // register 맵핑
    @GetMapping("/register")
    public String getRegister(){
        return "register";
    }
    // forgot-password 맵핑
    @GetMapping("/forgot-password")
    public String getForgotPassword(){
        return "forgot-password";
    }
    // 404 맵핑
    @GetMapping("/404")
    public String get404(){
        return "404";
    }
    // blank 맵핑
    @GetMapping("/blank")
    public String getBlank(){
        return "blank";
    }
    // tabales 맵핑
    @GetMapping("/tables")
    public String getTables(){
        return "tables";
    }
    // charts 맵핑
    @GetMapping("/charts")
    public String getCharts(){
        return "charts";
    }
}
