package iducs.springboot.bootjpa.service;

import iducs.springboot.bootjpa.domain.Board;
import iducs.springboot.bootjpa.domain.PageRequestDTO;
import iducs.springboot.bootjpa.domain.PageResultDTO;
import iducs.springboot.bootjpa.entity.BoardEntity;
import iducs.springboot.bootjpa.entity.MemberEntity;
import iducs.springboot.bootjpa.repository.BoardRepository;
import iducs.springboot.bootjpa.repository.ReplyRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.function.Function;

@Service
@Log4j2
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;
    private final ReplyRepository replyRepository;
    public BoardServiceImpl(BoardRepository boardRepository,ReplyRepository replyRepository) {
        this.boardRepository = boardRepository;
        this.replyRepository = replyRepository;
    }//생성자 주입

    @Override
    public Long register(Board dto) {
        log.info("board register : " + dto);
        BoardEntity boardEntity = dtoToEntity(dto);
        log.info("board register : " + boardEntity);
        boardRepository.save(boardEntity);
        return boardEntity.getBno();//게시물 번호
    }

    @Override
    public PageResultDTO<Board, Object[]> getList(PageRequestDTO pageRequestDTO) {
        log.info(">>>>>" + pageRequestDTO);
        Function<Object[], Board> fn =
                (entity -> entityToDto((BoardEntity) entity[0],
                        (MemberEntity) entity[1], (Long) entity[2]));
        Page<Object[]> result =
                boardRepository.getBoardWithReplyCount(pageRequestDTO.getPageable(Sort.by("bno").descending()));
        return new PageResultDTO<>(result, fn);
    }

    @Override
    public Board getById(Long bno) {
        Object result = boardRepository.getBoardWithWriter(bno);
        Object[] en = (Object[]) result;
        return entityToDto((BoardEntity) en[0],
                (MemberEntity) en[1], (Long) en[2]);//BoardDTO
    }

    @Override
    public Long modify(Board dto) {
        // Optional 클래스
        // non_null 값을 포함 또느 포함하지 않는 컨테이너 클래스
        // 결과 없음을 명확하게 표현하거나 null 사용이 오류를 발생할 수 있음을 메소드 반환 유형으로 사용할 목적으로 설계

        Optional<BoardEntity> result = boardRepository.findById(dto.getBno());
        BoardEntity boardEntity = null;
        if(result.isPresent()){
            boardEntity = (BoardEntity) result.get();
            boardEntity.changeTitle(dto.getTitle());
            boardEntity.changeContent(dto.getContent());
            boardRepository.save(boardEntity);
        }
        return boardEntity.getBno();
    }

    @Transactional
    @Override
    public void deleteWithRepliesById(Long bno) {
        replyRepository.deleteByBno(bno);// 댓글이 없을경우 오류발생후 꺼질 수도 있음
        // 이러한 오류들을 해결하기 위해서 Transactional을 어노테이션으로 써준다
        boardRepository.deleteById(bno);
    }


}
