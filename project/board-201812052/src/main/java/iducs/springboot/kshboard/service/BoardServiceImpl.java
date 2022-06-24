package iducs.springboot.kshboard.service;

import iducs.springboot.kshboard.domain.Board;
import iducs.springboot.kshboard.domain.PageRequestDTO;
import iducs.springboot.kshboard.domain.PageResultDTO;
import iducs.springboot.kshboard.entity.BoardEntity;
import iducs.springboot.kshboard.entity.MemberEntity;
import iducs.springboot.kshboard.repository.BoardRepository;
import iducs.springboot.kshboard.repository.ReplyRepository;
import iducs.springboot.kshboard.repository.SearchBoardRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.function.Function;

@Service
@Log4j2
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;
    private final ReplyRepository replyRepository;
    private final SearchBoardRepository searchBoardRepository;

    public BoardServiceImpl(BoardRepository boardRepository,
                            ReplyRepository replyRepository, @Qualifier("SearchBoardRepositoryImpl") SearchBoardRepository searchBoardRepository) {
        this.boardRepository = boardRepository;
        this.replyRepository = replyRepository;
        this.searchBoardRepository = searchBoardRepository;
    } // 생성자 주입

    @Override
    public Long register(Board dto) { // Controller -> DTO 객체 -> Service
        log.info("board register : " + dto);
        BoardEntity boardEntity = dtoToEntity(dto);
        boardRepository.save(boardEntity);
        return boardEntity.getBno(); // 게시물 번호
    }

    @Override
    public PageResultDTO<Board, Object[]> getList(PageRequestDTO pageRequestDTO) {
        log.info(">>>>>" + pageRequestDTO);
        // entites - Object[], dto - Board
        Function<Object[], Board> fn =
                (entities -> entityToDto((BoardEntity) entities[0],
                        (MemberEntity) entities[1], (Long) entities[2]));
        Page<Object[]> result;
        result = searchBoardRepository.searchPage(pageRequestDTO.getType(),pageRequestDTO.getKeyword(),pageRequestDTO.getPageable(Sort.by("views").descending()));

        /*if(pageRequestDTO.getSort()==null){
            result = searchBoardRepository.searchPage(pageRequestDTO.getType(),pageRequestDTO.getKeyword(),pageRequestDTO.getPageable(Sort.by("views").ascending()));
        }else if(pageRequestDTO.getSort()){
            result = searchBoardRepository.searchPage(pageRequestDTO.getType(),pageRequestDTO.getKeyword(),pageRequestDTO.getPageable(Sort.by("views").descending()));
        }else{
            result = searchBoardRepository.searchPage(pageRequestDTO.getType(),pageRequestDTO.getKeyword(),pageRequestDTO.getPageable(Sort.by("views").ascending()));
        }*/
        
        return new PageResultDTO<>(result, fn);
    }

    @Override
    public Board getById(Long bno) {
        Object result = boardRepository.getBoardByBno(bno);
        Object[] en = (Object[]) result;
        return entityToDto((BoardEntity) en[0],
                (MemberEntity) en[1], (Long) en[2]); // BoardDTO
    }
    @Override
    public Board getByIdAndReply(Long bno) {
        Object result = boardRepository.getBoardWithReply(bno);
        Object[] en = (Object[]) result;
        return entityToDto((BoardEntity) en[0],
                (MemberEntity) en[1], (Long) en[2]); // BoardDTO
    }

    @Override
    public Long modify(Board dto) {
        Optional<BoardEntity> result = boardRepository.findById(dto.getBno());
        BoardEntity boardEntity = null;
        if(result.isPresent()) {
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
        replyRepository.deleteByBno(bno);
        boardRepository.deleteById(bno); // board 레코드를 삭제
    }

    @Override
    public Long addViews(Long bno) {
        Optional<BoardEntity> result = boardRepository.findById(bno);
        BoardEntity boardEntity = null;
        if(result.isPresent()) {
            boardEntity = (BoardEntity) result.get();
            boardEntity.addViews();
            boardRepository.save(boardEntity);
            return boardEntity.getViews();
        }else{
            return null;
        }
    }

    @Override
    public void update(Board board) {
        log.info("board register : " + board);
        BoardEntity boardEntity = dtoToEntity(board);
        boardRepository.save(boardEntity);
    }
}

