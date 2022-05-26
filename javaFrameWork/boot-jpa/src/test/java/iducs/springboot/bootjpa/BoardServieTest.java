package iducs.springboot.bootjpa;

import iducs.springboot.bootjpa.domain.Board;
import iducs.springboot.bootjpa.domain.PageRequestDTO;
import iducs.springboot.bootjpa.domain.PageResultDTO;
import iducs.springboot.bootjpa.service.BoardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
public class BoardServieTest {

    @Autowired
    BoardService boardService;

    //@Test
    public void testRegister(){
        IntStream.rangeClosed(1,47).forEach(i->{
            Board dto = Board.builder()
                    .title("title"+i)
                    .content("Content...")
                    .writerSeq(Long.valueOf(""+i))
                    .build();
            Long bno = boardService.register(dto);
        });
    }

    @Test
    public void testList(){
        PageRequestDTO pageRequestDTO = new PageRequestDTO();
        pageRequestDTO.setPage(3);
        // entities - object[] , dto - Board
        PageResultDTO<Board,Object[]> result = boardService.getList(pageRequestDTO);
        for(Board dto : result.getDtoList())
            System.out.println(dto.getBno()+" "+ dto.getTitle());
    }
}
