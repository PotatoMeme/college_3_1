package iducs.springboot.kshboard.controller;

import iducs.springboot.kshboard.domain.Member;
import iducs.springboot.kshboard.domain.PageRequestDTO;
import iducs.springboot.kshboard.service.MemberService;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;

    // 생성자 주입 : (Constructor Injection)  vs. @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/regform")
    public String getRegform(HttpSession session,Model model) {
        Member dto = (Member) session.getAttribute("login");
        if (dto == null){
            model.addAttribute("member", Member.builder().build());
            return "/members/regform";
        }else if(dto.getId().equals("admin")){
            model.addAttribute("member", Member.builder().build());
            return "/members/regform";
        }else{
            return "redirect:/";
        }
    }

    @GetMapping("/login")
    public String getLogin(Model model) {
        // 정보를 전달받을 객체를 보냄
        model.addAttribute("member", Member.builder().build());
        return "/members/login";
    }
    @GetMapping("/loginfail")
    public String getLoginFail(Model model) {
        // 정보를 전달받을 객체를 보냄
        model.addAttribute("member", Member.builder().build());
        return "/members/login";
    }
    @PostMapping("/login")
    public String postLogin(@ModelAttribute("member") Member member, HttpServletRequest request) {
        Member dto = null;
        if((dto = memberService.loginByEmail(member)) != null) {
            HttpSession session = request.getSession();
            if (dto.getBan() == true) {
                return "/members/loginban";
            }
            session.setAttribute("login", dto);
            if(dto.getId().contains("admin"))
                session.setAttribute("isadmin", dto.getId());

            return "redirect:/";
        }
        else
            return "/members/loginfail";
    }
    @GetMapping("/logout")
    public String getLogout(HttpSession session) {
        session.invalidate();
        return "redirect:/"; // view resolving
    }


    @PostMapping("")
    public String postMember(@ModelAttribute("member") Member member, Model model) {
        memberService.create(member);
        member.setBan(false);
        member.setLevel("1");
        model.addAttribute("member", member); // 입력한 객체를 전달, DB로 부터 가져온 것 아님
        return "/members/info";
    }

    @GetMapping("")
    public String getMemebers(HttpSession session,PageRequestDTO pageRequestDTO, Model model) {
        Member dto = (Member) session.getAttribute("login");
        if (dto == null){
            return "redirect:/members/login";
        }else if(dto.getId().equals("admin")){
            model.addAttribute("list", memberService.readListBy(pageRequestDTO));
            return "/members/members";
        }else{
            return "redirect:/";
        }

    }
    @GetMapping("/{seq}")
    public String getMemeber(HttpSession session,@PathVariable("seq") Long seq,Model model) {
        Member dto = (Member) session.getAttribute("login");
        if (dto == null){
            return "redirect:/members/login";
        }
        if(dto.getSeq() == seq || dto.getId().equals("admin")){
            Member member = memberService.readById(seq);
            model.addAttribute("member", member);
            return "/members/member";
        }else{
            return "redirect:/";
        }
    }

    @GetMapping("/{seq}/upform")
    public String getUpform(HttpSession session,@PathVariable("seq") Long seq,Model model) {
        // 정보를 전달받을 객체를 보냄
        Member dto = (Member) session.getAttribute("login");
        if (dto == null){
            return "redirect:/members/login";
        }else if(dto.getSeq() == seq||dto.getId().equals("admin")){
            Member member = memberService.readById(seq);
            model.addAttribute("member", member);
            return "/members/upform";
        }else{
            return "redirect:/";
        }
    }

    @PutMapping("/{seq}")
    public String putMember(HttpSession session,@PathVariable("seq") Long seq,@ModelAttribute("member") Member member,Model model) {
        // 정보를 전달받을 객체를 보냄
        Member dto = (Member) session.getAttribute("login");
        Member member1 = memberService.readById(seq);
        if (member.getPw().equals("")) member.setPw(member1.getPw());
        if(dto.getSeq() == member.getSeq()){
            member.setBan(false);
            session.setAttribute("login", member);
        }
        memberService.update(member);
        model.addAttribute(member);
        return "redirect:/members/"+ member.getSeq();
    }

    /* 모달사용중 안씀
    @GetMapping("/{seq}/delform")
    public String getDelform(HttpSession session,@PathVariable("seq") Long seq,Model model) {
        // 정보를 전달받을 객체를 보냄
        Member dto = (Member) session.getAttribute("login");
        if (dto == null){
            return "redirect:/members/login";
        }
        Member member = memberService.readById(seq);
        model.addAttribute("member", member);
        return "/members/delform";
    }*/
    @DeleteMapping("/{seq}")
    public String delMember(HttpSession session,@ModelAttribute("member") Member member,Model model) {
        // 정보를 전달받을 객체를 보냄
        Member dto = (Member) session.getAttribute("login");
        if (dto == null){
            return "redirect:/members/login";
        }else if(dto.getSeq() == member.getSeq()){
            memberService.delete(member);
            session.invalidate();
            return "redirect:/";
        }else{
            if(dto.getId().equals("admin")){
                memberService.delete(member);
                return "redirect:/members";
            }else{
                return "redirect:/";
            }
        }
    }

}

