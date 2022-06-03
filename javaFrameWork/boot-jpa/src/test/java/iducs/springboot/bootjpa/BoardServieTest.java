package iducs.springboot.bootjpa;

import iducs.springboot.bootjpa.domain.Board;
import iducs.springboot.bootjpa.domain.PageRequestDTO;
import iducs.springboot.bootjpa.domain.PageResultDTO;
import iducs.springboot.bootjpa.entity.BoardEntity;
import iducs.springboot.bootjpa.repository.BoardRepository;
import iducs.springboot.bootjpa.service.BoardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Random;
import java.util.stream.IntStream;

@SpringBootTest
public class BoardServieTest {

    @Autowired
    BoardService boardService;

    @Autowired
    BoardRepository boardRepository;

    //@Test
    public void testRegister(){
        IntStream.rangeClosed(1,47).forEach(i->{
            Long seqLong = Long.valueOf(new Random().nextInt(50));
            seqLong = (seqLong == 0)? 1: seqLong;
            Board dto = Board.builder()
                    .title("title"+i)
                    .content("Content...")
                    .writerSeq(seqLong)// member entity seq 값이 존재해야함
                    .build();
            Long bno = boardService.register(dto);
        });
    }

    //@Test
    public void testList(){
        PageRequestDTO pageRequestDTO = new PageRequestDTO();
        pageRequestDTO.setPage(3);
        pageRequestDTO.setSize(4);// 47 - 8  = 39
        // entities - object[] , dto - Board
        PageResultDTO<Board,Object[]> result = boardService.getList(pageRequestDTO);
        for(Board dto : result.getDtoList()) // 39~36
            System.out.println(dto.getBno()+" "+ dto.getTitle());
    }

    @Transactional // 즉시 로딩 조인을 할경우 성능 감고 조인이 쌓이다보면 서버가 터질수도
    @Test
    public void  testLazyLoading(){
        Long bno = Long.valueOf(10);
        //Optional<BoardEntity> result = boardService.getById(bno);
        Optional<BoardEntity> result = boardRepository.findById(bno);
        BoardEntity boardEntity = result.get();

        System.out.println(boardEntity.getTitle()); // title10
        System.out.println(boardEntity.getContent()); // Content...
        System.out.println(boardEntity.getWriter()); // -> join연산을 해야함 Lazy라면 오류 발생
        // MemberEntity(seq=46, id=id 46, pw=pw 46, name=name 46, email=email 4, phone=phone 26, address=address 46)
    }
    @Test
    public void  testDeleteWithReplies(){
        Long bno = 3L;
        boardService.deleteWithRepliesById(bno);
    }
}
