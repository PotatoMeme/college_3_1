package iducs.springboot.kshboard.service;

import iducs.springboot.kshboard.domain.Board;
import iducs.springboot.kshboard.domain.Member;
import iducs.springboot.kshboard.domain.Reply;
import iducs.springboot.kshboard.entity.BoardEntity;
import iducs.springboot.kshboard.entity.MemberEntity;
import iducs.springboot.kshboard.entity.ReplyEntity;

import java.util.List;

public interface ReplyService {


    default ReplyEntity dtoToEntity(Reply dto) { // interface default 메소드
        MemberEntity member = MemberEntity.builder()
                .seq(dto.getWriterSeq())
                .build();
        BoardEntity board = BoardEntity.builder()
                .bno(dto.getBoardBno())
                .build();
        ReplyEntity replyEntity =ReplyEntity.builder()
                .rno(dto.getRno())
                .text(dto.getText())
                .writer(member)
                .board(board)
                .build();
        return replyEntity;
    }

    default Reply entityToDto(ReplyEntity entity, MemberEntity member, BoardEntity board) {
        Reply dto = Reply.builder()
                .rno(entity.getRno())
                .text(entity.getText())
                .writerSeq(member.getSeq())
                .writerId(member.getId())
                .boardBno(board.getBno())
                .regDate(entity.getRegDate())
                .modDate(entity.getModDate())
                .build();
        return dto;
    }

    List<Reply> getRepliesWithBno(Long bno);
    boolean deleteConstraints(Long bno);
    boolean deleteOne(Long rno);
    void register(Reply reply, Member dto, Long bno);

    Reply getReply(Long rno);

    void update(Reply reply, Member dto, Long bno);
}
