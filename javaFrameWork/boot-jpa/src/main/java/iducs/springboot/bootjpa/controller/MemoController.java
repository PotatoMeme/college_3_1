package iducs.springboot.bootjpa.controller;

import iducs.springboot.bootjpa.domain.Member;
import iducs.springboot.bootjpa.domain.Memo;
import iducs.springboot.bootjpa.service.MemoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/memos")
public class MemoController {
    // MemoService 인터페이스로 부터 구현가능한 객체를 생성해서 주입
    // Spring Bean 객체로 구현하였기 때문에 Spring이 주입할 수 있음
    final MemoService memoService; // 참조변수명 memoService

    public MemoController(MemoService memoService) {
        this.memoService = memoService;
    }
    // registration form
    @GetMapping("/regform")
    public String getRegform(Model model) {
        model.addAttribute("memo", Memo.builder().build());
        return "/memos/regform";
    }
    //HttpServletRequest request
    @PostMapping("")
    public String postMemo(@ModelAttribute("memo") Memo memo, Model model ) {
        memoService.create(memo);
        model.addAttribute("memo", memo);
        return "/memos/memo";
    }
    @GetMapping("")
    public String getMemos(Model model) {
        // 정보를 전달받을 객체를 보냄
        List<Memo> memos = memoService.readAll();
        model.addAttribute("list", memos);
        return "/memos/memos";
    }
    @GetMapping("/{idx}")
    public String getMemo(@PathVariable("idx") Long mno, Model model) {
        Memo memo = memoService.readById(mno);
        model.addAttribute("memo", memo);
        return "/memos/memo";
    }
    // localhost:8888/memos/13/upform
    @GetMapping("/{idx}/upform")
    public String getUpform(@PathVariable("idx") Long mno, Model model) {
        Memo memo = memoService.readById(mno); // entity -> domain
        model.addAttribute("memo", memo);
        return "/memos/upform";
    }
    @PutMapping("/{idx}")
    public String putMemo(@ModelAttribute("memo") Memo memo, Model model) {
        memoService.update(memo);
        model.addAttribute("memo", memo);
        return "/memos/memo";
    }

    @GetMapping("/{mno}/delform")
    public String getDelform(@PathVariable("mno") Long mno,Model model) {
        // 정보를 전달받을 객체를 보냄
        Memo memo = memoService.readById(mno);
        model.addAttribute("memo", memo);
        return "/memos/delform";
    }
    @DeleteMapping("/{mno}")
    public String delMember(@ModelAttribute("memo") Memo memo,Model model) {
        // 정보를 전달받을 객체를 보냄
        memoService.delete(memo);
        return "redirect:/memos";
    }
}

