package iducs.springboot.bootjpa.service;

import iducs.springboot.bootjpa.domain.Board;
import iducs.springboot.bootjpa.domain.PageRequestDTO;
import iducs.springboot.bootjpa.domain.PageResultDTO;
import iducs.springboot.bootjpa.entity.BoardEntity;
import iducs.springboot.bootjpa.entity.MemberEntity;

public interface BoardService {
    Long register(Board dto);
    PageResultDTO<Board,Object[]> getList(PageRequestDTO pageRequestDTO);

    Board getById(Long bno);
    Long modify(Board dto);
    void deleteWithRepliesById(Long bno);

    default BoardEntity dtoToEntity(Board dto){
        MemberEntity member = MemberEntity.builder()
                .seq(dto.getWriterSeq())
                .build();

        BoardEntity boardEntity = BoardEntity.builder()
                .bno(dto.getBno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(member)
                .build();
        return boardEntity;
    }
    default Board entityToDto(BoardEntity entity,MemberEntity member, Long replyCount){
        Board dto = Board.builder()
                .bno(entity.getBno())
                .title(entity.getTitle())
                .content(entity.getContent())
                .writerSeq(member.getSeq())
                .writerId(member.getId())
                .writerName(member.getName())
                .writerEmail(member.getEmail())
                .regDate(entity.getRegDate())
                .modDate(entity.getModDate())
                .replyCount(replyCount.intValue())
                .build();
        return dto;
    }

}
