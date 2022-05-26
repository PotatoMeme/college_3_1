package iducs.springboot.bootjpa.controller;

import iducs.springboot.bootjpa.domain.Member;
import iducs.springboot.bootjpa.domain.PageRequestDTO;
import iducs.springboot.bootjpa.service.BoardService;
import iducs.springboot.bootjpa.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/boards")
public class BoardController {
    public final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }


    @GetMapping("")
    public String getBoards(PageRequestDTO pageRequestDTO, Model model) {
        // ?page=nn&&size=mm
        // mm = 5페이지 단위로  nn =  3페이지를 접근
        // new PageRequest(3,5) : 생성자를 호출하여 초기화
        // 11 ~ 15 번째의 레코드들을 접근함

        model.addAttribute("list", boardService.getList(pageRequestDTO));
        return "/boards/boards";
    }
}
