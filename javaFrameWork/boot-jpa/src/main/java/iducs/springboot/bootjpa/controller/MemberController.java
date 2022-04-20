package iducs.springboot.bootjpa.controller;

import iducs.springboot.bootjpa.entity.MemberEntity;
import iducs.springboot.bootjpa.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/members")
public class MemberController {
    @Autowired
    MemberService memberService;
    // index 맵핑
    @GetMapping("/")
    public String getIndex(Model model){
        List<MemberEntity> list = memberService.readAll();
        model.addAttribute("members",list);
        return "index";
    }
    @GetMapping("/index")
    public String getIndexs(){
        return "index";
    }
    //
    @GetMapping("/th")
    public String getThymeleaf(Model model){
        return "thymeleaf";
    }


    //
    @GetMapping("/{idx}")
    public String getMember(@PathVariable("idx") Long seq,Model model){
        Optional<MemberEntity> member = memberService.readById(seq);
        MemberEntity m = member.get();
        model.addAttribute("member",m);
        return "charts";
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
