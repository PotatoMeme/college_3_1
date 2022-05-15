package iducs.springboot.bootjpa.controller;

import iducs.springboot.bootjpa.domain.Member;
import iducs.springboot.bootjpa.domain.PageRequestDTO;
import iducs.springboot.bootjpa.domain.PageResultDTO;
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
        //return "/members/regform";
        return "/members/regform";
    }

    @PostMapping("")
    public String postMember(@ModelAttribute("member") Member member, Model model) {
        memberService.create(member);
        model.addAttribute("member", member); // 입력한 객체를 전달, DB로 부터 가져온 것 아님
        return "/members/info";
    }

    @GetMapping("")
    public String getMemebers(PageRequestDTO pageRequestDTO, Model model) {
        // 정보를 전달받을 객체를 보냄
        //List<Member> members = memberService.readAll();
        //model.addAttribute("list", members);
        model.addAttribute("list", memberService.readListBy(pageRequestDTO));
        return "/members/members";
    }
    @GetMapping("/{seq}")
    public String getMemeber(@PathVariable("seq") Long seq,Model model) {
        // 정보를 전달받을 객체를 보냄
        Member member = memberService.readById(seq);
        model.addAttribute("member", member);
        return "/members/member";
    }

    @GetMapping("/{seq}/upform")
    public String getUpform(@PathVariable("seq") Long seq,Model model) {
        // 정보를 전달받을 객체를 보냄
        Member member = memberService.readById(seq);
        model.addAttribute("member", member);
        return "/members/upform";
    }

    @PutMapping("/{seq}")
    public String putMember(@ModelAttribute("member") Member member,Model model) {
        // 정보를 전달받을 객체를 보냄
        memberService.update(member);
        model.addAttribute(member);
        return "redirect:/members";
    }
    @GetMapping("/{seq}/delform")
    public String getDelform(@PathVariable("seq") Long seq,Model model) {
        // 정보를 전달받을 객체를 보냄
        Member member = memberService.readById(seq);
        model.addAttribute("member", member);
        return "/members/delform";
    }
    @DeleteMapping("/{seq}")
    public String delMember(@ModelAttribute("member") Member member,Model model) {
        // 정보를 전달받을 객체를 보냄
        memberService.delete(member);
        return "redirect:/members";
    }

}

