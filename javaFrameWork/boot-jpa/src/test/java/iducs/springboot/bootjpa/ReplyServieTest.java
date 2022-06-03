package iducs.springboot.bootjpa;

import iducs.springboot.bootjpa.domain.Board;
import iducs.springboot.bootjpa.domain.PageRequestDTO;
import iducs.springboot.bootjpa.domain.PageResultDTO;
import iducs.springboot.bootjpa.entity.BoardEntity;
import iducs.springboot.bootjpa.entity.ReplyEntity;
import iducs.springboot.bootjpa.repository.BoardRepository;
import iducs.springboot.bootjpa.repository.ReplyRepository;
import iducs.springboot.bootjpa.service.BoardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Random;
import java.util.stream.IntStream;

@SpringBootTest
public class ReplyServieTest {

    @Autowired
    BoardService boardService;

    @Autowired
    ReplyRepository replyRepository;


    @Test
    public void testReplyInit(){
        IntStream.rangeClosed(1,47).forEach(i->{
            Long seqLong = Long.valueOf(new Random().nextInt(47));
            seqLong = (seqLong == 0)? 1: seqLong;
            BoardEntity boardEntity = BoardEntity.builder()
                    .bno(seqLong)
                    .build();
            ReplyEntity replyEntity = ReplyEntity.builder()
                    .text("text"+i)
                    .replier("reply")
                    .board(boardEntity)
                    .build();
            replyRepository.save(replyEntity);
        });
    }
}
