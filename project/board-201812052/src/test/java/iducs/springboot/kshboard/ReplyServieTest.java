package iducs.springboot.kshboard;

import iducs.springboot.kshboard.domain.Board;
import iducs.springboot.kshboard.domain.Member;
import iducs.springboot.kshboard.domain.Reply;
import iducs.springboot.kshboard.entity.BoardEntity;
import iducs.springboot.kshboard.entity.MemberEntity;
import iducs.springboot.kshboard.entity.ReplyEntity;
import iducs.springboot.kshboard.repository.ReplyRepository;
import iducs.springboot.kshboard.service.BoardService;
import iducs.springboot.kshboard.service.ReplyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

@SpringBootTest
public class ReplyServieTest {

    @Autowired
    BoardService boardService;
    @Autowired
    ReplyService replyService;

    @Autowired
    ReplyRepository replyRepository;


    @Test
    public void testReplyInit(){
        IntStream.rangeClosed(1, 30).forEach(i -> {
            Long seqLong1 = Long.valueOf(new Random().nextInt(20));
            Long seqLong2 = Long.valueOf(new Random().nextInt(20));
            seqLong1 = (seqLong1 == 0) ? 1: seqLong1;
            seqLong2 = (seqLong2 == 0) ? 1: seqLong2;
            MemberEntity memberEntity = MemberEntity.builder()
                    .seq(seqLong1)
                    .build();
            BoardEntity boardEntity = BoardEntity.builder()
                    .bno(seqLong2)
                    .build();
            ReplyEntity replyEntity = ReplyEntity.builder()
                    .text("title" + i)
                    .writer(memberEntity)
                    .board(boardEntity) // member entity seq 값이 존재해야 함
                    .build();
            replyRepository.save(replyEntity);
        });
    }
    @Test
    public void printReply(){
        Object[] result = replyRepository.getReplyWithBno(3l);
        Reply reply;
        for (Object index : result) {
            Object[] indexes = (Object[]) index;
            reply = replyService.entityToDto((ReplyEntity) indexes[0], (MemberEntity) indexes[1], (BoardEntity) indexes[2]);
            System.out.println(reply.getRno());
        }
    }
}
