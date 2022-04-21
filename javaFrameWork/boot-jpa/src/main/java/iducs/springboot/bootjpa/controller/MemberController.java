package iducs.springboot.bootjpa.controller;

import iducs.springboot.bootjpa.domain.Member;
import iducs.springboot.bootjpa.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;

    // 생성자 주입 : (Constructor Injection)  vs. @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/regform")
    public String getRegform(Model model) {
        // 정보를 전달받을 객체를 보냄
        model.addAttribute("member", Member.builder().build());
        return "/members/regform";
    }

    @PostMapping("")
    public String postMember(@ModelAttribute("member") Member member, Model model) {
        memberService.create(member);
        model.addAttribute("member", member); // 입력한 객체를 전달, DB로 부터 가져온 것 아님
        return "/members/info";
    }
}

