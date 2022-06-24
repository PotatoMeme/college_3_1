package iducs.springboot.kshboard.repository;

import iducs.springboot.kshboard.entity.BoardEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SearchBoardRepository {
    BoardEntity searchBoard();
    Page<Object[]> searchPage(String type, String keyword, Pageable pageable);
}
