package iducs.springboot.kshboard.controller;

import iducs.springboot.kshboard.domain.Member;
import iducs.springboot.kshboard.domain.Reply;
import iducs.springboot.kshboard.service.MemberService;
import iducs.springboot.kshboard.service.ReplyService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
@Log4j2
@Controller
@RequestMapping("/replies")
public class ReplyController {

    public final MemberService memberService;
    public final ReplyService replyService;

    public ReplyController(MemberService memberService, ReplyService replyService) {
        this.memberService = memberService;
        this.replyService = replyService;
    } // 생성자 주입

    @PostMapping("/{bno}")
    public String postReply(HttpSession session, @PathVariable("bno") Long bno, @ModelAttribute("reg") Reply reply) {
        Member dto = (Member) session.getAttribute("login");
        replyService.register(reply, dto, bno);
        return "redirect:/boards/" + bno; // 다시 get 방식으로 해당 URI로 요청
    }
    @PutMapping("/{rno}")
    public String putReply(HttpSession session, @PathVariable("rno") Long rno, @ModelAttribute("reply") Reply reply) {
        Member dto = (Member) session.getAttribute("login");
        if (dto == null){
            return "redirect:/";
        }else {
            Member member = memberService.readById(reply.getWriterSeq());
            if (dto.getId().equals("admin") || dto.getSeq() == member.getSeq()) {
                Long bno = reply.getBoardBno();
                log.info("Bno:"+bno);
                replyService.update(reply, member, bno);
                return "redirect:/boards/" + bno; // 다시 get 방식으로 해당 URI로 요청
            }else{
                return "redirect:/";
            }
        }
    }
    @DeleteMapping("/{rno}")
    public String deletReply(@PathVariable("rno") Long rno, @ModelAttribute("reply") Reply reply) {
        Long bno = reply.getBoardBno();
        replyService.deleteOne(rno);
        return "redirect:/boards/" + bno; // 다시 get 방식으로 해당 URI로 요청
    }

    @GetMapping("/{rno}/upform")
    public String getUpform(HttpSession session,@PathVariable("rno") Long rno, Model model) {
        Member dto = (Member) session.getAttribute("login");
        if(dto == null){
            return "redirect:/";
        }else {
            Reply reply = replyService.getReply(rno);
            if (dto.getId().equals("admin") || dto.getSeq() == reply.getWriterSeq()) {
                model.addAttribute("reply", reply);
                return "/replies/upform"; // 다시 get 방식으로 해당 URI로 요청
            }else{
                return "redirect:/";
            }
        }

    }
    @GetMapping("/{rno}/delform")
    public String getDelform(HttpSession session,@PathVariable("rno") Long rno, Model model) {
        Member dto = (Member) session.getAttribute("login");
        if(dto == null){
            return "redirect:/";
        }else {
            Reply reply = replyService.getReply(rno);
            if (dto.getId().equals("admin") || dto.getSeq() == reply.getWriterSeq()) {
                model.addAttribute("reply", reply);
                return "/replies/delform"; // 다시 get 방식으로 해당 URI로 요청
            }else{
                return "redirect:/";
            }
        }

    }
}
