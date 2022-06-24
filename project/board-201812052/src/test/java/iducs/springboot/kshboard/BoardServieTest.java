package iducs.springboot.kshboard;

import iducs.springboot.kshboard.domain.Board;
import iducs.springboot.kshboard.domain.PageRequestDTO;
import iducs.springboot.kshboard.domain.PageResultDTO;
import iducs.springboot.kshboard.entity.BoardEntity;
import iducs.springboot.kshboard.repository.BoardRepository;
import iducs.springboot.kshboard.service.BoardService;
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

    @Test
    public void testInit() {
        IntStream.rangeClosed(1, 30).forEach(i -> {
            Long seqLong = Long.valueOf(new Random().nextInt(20));
            seqLong = (seqLong == 0) ? 1: seqLong;
            Board dto = Board.builder()
                    .title("title" + i)
                    .content("Content...")
                    .views(0l)
                    .ban(false)
                    .writerSeq(seqLong) // member entity seq 값이 존재해야 함
                    .build();
            Long bno = boardService.register(dto);
        });
    }

    @Test
    public void testList() {
        PageRequestDTO pageRequestDTO = new PageRequestDTO();
        pageRequestDTO.setPage(3); // 현재 페이지를 1 -> 3 설정
        pageRequestDTO.setSize(4); // 47 - 8 = 39
        PageResultDTO<Board, Object[]> result = boardService.getList(pageRequestDTO);
        for(Board dto : result.getDtoList())
            System.out.println(dto.getBno() +" : " + dto.getTitle());
    }

    @Transactional
    @Test
    public void testLazyloading() {
        Optional<BoardEntity> result = boardRepository.findById(10L);
        BoardEntity boardEntity = result.get();
        System.out.println(boardEntity.getTitle());
        System.out.println(boardEntity.getContent());
        //System.out.println(boardEntity.getWriter());
    }

    @Test
    public void testDeleteWithRepliesById() {
        Long bno = 3L;
        boardService.deleteWithRepliesById(bno);
    }

}
