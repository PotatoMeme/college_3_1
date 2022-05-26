package iducs.springboot.bootjpa.service;

import iducs.springboot.bootjpa.domain.Board;
import iducs.springboot.bootjpa.domain.PageRequestDTO;
import iducs.springboot.bootjpa.domain.PageResultDTO;
import iducs.springboot.bootjpa.entity.BoardEntity;
import iducs.springboot.bootjpa.entity.MemberEntity;
import iducs.springboot.bootjpa.repository.BoardRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@Log4j2
public class BoardServiceImpl implements BoardService{
    private final BoardRepository boardRepository;
    public BoardServiceImpl(BoardRepository boardRepository){this.boardRepository = boardRepository;}//생성자 주입

    @Override
    public Long register(Board dto) {
        log.info("board register : "+ dto);
        BoardEntity boardEntity = dtoToEntity(dto);
        log.info("board register : "+ boardEntity);
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
}
