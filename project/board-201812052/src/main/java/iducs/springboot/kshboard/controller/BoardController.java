package iducs.springboot.kshboard.controller;

import iducs.springboot.kshboard.domain.Board;
import iducs.springboot.kshboard.domain.Member;
import iducs.springboot.kshboard.domain.PageRequestDTO;
import iducs.springboot.kshboard.domain.Reply;
import iducs.springboot.kshboard.service.BoardService;
import iducs.springboot.kshboard.service.ReplyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/boards")
public class BoardController {
    public final BoardService boardService;
    public final ReplyService replyService;
    public BoardController(BoardService boardService, ReplyService replyService){
        this.boardService = boardService;
        this.replyService = replyService;
    } // 생성자 주입

    @GetMapping("/regform")
    public String getRegform(HttpSession session,Model model) {
        Member member = (Member) session.getAttribute("login");
        if (member == null){
            return "redirect:/";
        }else{
            model.addAttribute("dto", Board.builder().build()); // Board 객체 생성, 빈(empty)
            return "/boards/regform";
        }

    }

    @PostMapping("")
    public String postBoard(@ModelAttribute("dto") Board board) {
        board.setBan(false);
        board.setViews(0l);
        boardService.register(board);
        return "redirect:/boards"; // 다시 get 방식으로 해당 URI로 요청
    }
    @GetMapping("/{bno}/upform") // URI -> /boards
    public String udateBoard(HttpSession session,@PathVariable("bno") Long bno,Model model) {
        Member member = (Member) session.getAttribute("login");
        if (member == null){
            return "redirect:/";
        } else{
            Board board = boardService.getById(bno);
            if (member.getId().equals("admin") || member.getSeq() == board.getWriterSeq()|| (board.getBan() == false && board.getWriterBan() == false)) {
                model.addAttribute("dto", board);
                return "/boards/upform"; // view resolving
            }else{
                return "redirect:/";
            }
        }
    }
    @GetMapping("/{bno}/ban") // URI -> /boards
    public String makeBoardBan(HttpSession session,@PathVariable("bno") Long bno,Model model) {
        Member member = (Member) session.getAttribute("login");
        if (member == null){
            return "redirect:/";
        } else{
            Board board = boardService.getById(bno);
            if (member.getId().equals("admin")) {
                board.setBan(true);
                boardService.update(board);
                return "redirect:/boards/"+bno; // view resolving
            }else{
                return "redirect:/";
            }
        }
    }
    @GetMapping("/{bno}/unlock") // URI -> /boards
    public String unlockBoardBan(HttpSession session,@PathVariable("bno") Long bno,Model model) {
        Member member = (Member) session.getAttribute("login");
        if (member == null){
            return "redirect:/";
        } else{
            Board board = boardService.getById(bno);
            if (member.getId().equals("admin")) {
                board.setBan(false);
                boardService.update(board);
                return "redirect:/boards/"+bno; // view resolving
            }else{
                return "redirect:/";
            }
        }
    }
    @PutMapping("/{bno}") // URI -> /boards
    public String updateBoard(@ModelAttribute("dto") Board board) {
        boardService.update(board);
        return "redirect:/boards/"+board.getBno(); // 다시 get 방식으로 해당 URI로 요청
    }
    @DeleteMapping("/{bno}")
    public String delBoard(@PathVariable("bno") Long bno) {
        // 정보를 전달받을 객체를 보냄
        boardService.deleteWithRepliesById(bno);
        return "redirect:/boards";
    }
    @GetMapping("/{bno}/delform") // URI -> /boards
    public String getDelform(HttpSession session,@PathVariable("bno") Long bno, Model model) {
        Member member = (Member) session.getAttribute("login");
        if (member == null){
            return "redirect:/";
        } else{
            Board board = boardService.getById(bno);
            if (member.getId().equals("admin") || member.getSeq() == board.getWriterSeq() || (board.getBan() == false && board.getWriterBan() == false)) {
                model.addAttribute("bno", bno);
                return "/boards/delform"; // view resolving
            }else{
                return "redirect:/";
            }
        }

    }
    @GetMapping("") // URI -> /boards
    public String getBoards(PageRequestDTO pageRequestDTO, Model model) {
        // ?page=nn&size=mm
        // 만약 mm = 5페이지 단위로 nn = 3페이지를 접근
        // new PageRequestDTO(3, 5) : 생성자를 호출하여 초기화
        // 11 ~ 15 번째의 레코드들을 접근함
        // PageRequestDTO.builder().build() or new PageRequestDTO()가 pageRequestDTO 매개변수에 배정
        model.addAttribute("list", boardService.getList(pageRequestDTO));
        return "/boards/list"; // view resolving
    }
    @GetMapping("/{bno}") // URI -> /boards
    public String getBoard(HttpSession session,@PathVariable("bno") Long bno, Model model) {
        Member member = (Member) session.getAttribute("login");
        Board board = boardService.getById(bno);
        if (member == null){
            if(board.getBan() == false && board.getWriterBan() == false){
                board.setViews(boardService.addViews(bno));
                model.addAttribute("dto", board);
                model.addAttribute("replies", replyService.getRepliesWithBno(bno));
                model.addAttribute("reg", Reply.builder().build());
                return "/boards/read"; // view resolving
            }else{
                return "redirect:/";
            }
        } else{
            if (member.getId().equals("admin") || (board.getBan() == false && board.getWriterBan() == false) ) {
                board.setViews(boardService.addViews(bno));
                model.addAttribute("dto", board);
                model.addAttribute("replies", replyService.getRepliesWithBno(bno));
                model.addAttribute("reg", Reply.builder().build());
                return "/boards/read"; // view resolving
            }else{
                return "redirect:/";
            }
        }

    }
}
