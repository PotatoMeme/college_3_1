package iducs.springboot.kshboard.service;

import iducs.springboot.kshboard.domain.Board;
import iducs.springboot.kshboard.domain.PageRequestDTO;
import iducs.springboot.kshboard.domain.PageResultDTO;
import iducs.springboot.kshboard.entity.BoardEntity;
import iducs.springboot.kshboard.entity.MemberEntity;

public interface BoardService {
    Long register(Board dto); // Borad : DTO or Domain , create
    PageResultDTO<Board, Object[]> getList(PageRequestDTO pageRequestDTO); //read list
    Board getById(Long bno);

    Board getByIdAndReply(Long bno);

    Long modify(Board dto);
    void deleteWithRepliesById(Long bno);
    Long addViews(Long bno);

    default BoardEntity dtoToEntity(Board dto) { // interface default 메소드
        MemberEntity member = MemberEntity.builder()
                .seq(dto.getWriterSeq())
                .build();
        BoardEntity boardEntity = BoardEntity.builder()
                .bno(dto.getBno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .views(dto.getViews())
                .ban(dto.getBan())
                .writer(member)
                .build();
        return boardEntity;
    }
    default Board entityToDto(BoardEntity entity, MemberEntity member, Long replyCount) {
        Board dto = Board.builder()
                .bno(entity.getBno())
                .title(entity.getTitle())
                .views(entity.getViews())
                .ban(entity.getBan())
                .content(entity.getContent())
                .writerSeq(member.getSeq())
                .writerId(member.getId())
                .writerName(member.getName())
                .writerEmail(member.getEmail())
                .writerBan(member.getBan())
                .regDate(entity.getRegDate())
                .modDate(entity.getModDate())
                .replyCount(replyCount.intValue())
                .build();
        return dto;
    }

    void update(Board board);
}
