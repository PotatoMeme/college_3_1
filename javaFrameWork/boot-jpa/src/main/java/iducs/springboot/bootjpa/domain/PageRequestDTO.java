package iducs.springboot.bootjpa.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Pageable;

@Builder
@AllArgsConstructor
@Data //@Getter, @Setter, @EqualsAndHash, @RequiredArgsConstructor
public class PageRequestDTO { // 페이지 처리를 위한 객체
    private int page; // 요청하는 페이지
    private int size; // 한 페이지에 나타나는 수
    private String type; // e - email,a - address 검색항목
    private String keyword; // 검색어
    private int sorter;

    public PageRequestDTO() {
        this.page = 1;
        this.size = 6;
    }
    public int getSort(){return sorter;}
    public Pageable getPageable(Sort sort) {
        return PageRequest.of(page - 1, size, sort);
    }
}

