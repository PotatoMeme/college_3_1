package iducs.springboot.kshboard.domain;

import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data
public class PageResultDTO<DTO, EN> { // Generics
    private List<DTO> dtoList;
    private int totalPage; // 총 페이지 수

    private int currentPage; // 현재 페이지
    private int sizeOfPage; // 페이지 당 크기

    private int startPage, endPage; // 페이지 목록의 시작 페이지 번호, 마지막 페이지 번호
    private boolean prevPage, nextPage;// 이전 페이지 또는 다음 페이지 존재 유무
    // 페이지 번호 목록
    private List<Integer> pageList;
    public PageResultDTO(Page<EN> result, Function<EN, DTO> fn) { // 결과 페이지 객체를 초기화하는 생성자
        dtoList = result.stream().map(fn).collect(Collectors.toList());
        totalPage = result.getTotalPages();
        makePageList(result.getPageable());
    }

    public void makePageList(Pageable pageable) {
        this.currentPage = pageable.getPageNumber() + 1;
        this.sizeOfPage = pageable.getPageSize();
        double pageDouble = (double) sizeOfPage;
        // sizeOfPage = 10, pageDouble 10.0, currentPage = 2
        // 2 / 10.0 = 0.2 -> Math.ceil(0.2) 올림 : 1 * 10 = 10(일시적인 마지막 페이지)

        // currentPage = 12, 12 / 10.0 = 1.2 -> Math.ceil(1.2) 올림 2 * 10 : 20
        // startPage 11, end 20

        // 페이지 목록 번호 크기(sizeOfPage) 5 -> 5.0
        // 현재 페이지 3, 1 ~ 5 페이지 목록 : 3/5.0 = 0.6 -> ceil() 올림 ㅣ 1
        // 마지막 페이지 1*5(페이지 목록 번호 크기) = 5
        // 현재 페이지 7, 6 ~ 10 페이지 목록 : 7/5.0 = 1.4 -> ceil() 올림 ㅣ 2
        // 마지막 페이지 2*5(페이지 목록 번호 크기) = 10
        // totalPage


        int tempEnd = (int)(Math.ceil(currentPage/pageDouble)) * sizeOfPage;

        startPage = tempEnd - (sizeOfPage - 1);
        endPage = (totalPage > tempEnd) ? tempEnd: totalPage;
        prevPage = startPage > 1;
        nextPage = totalPage > tempEnd;

        // 아래쪽 Pagination 처리 시 사용
        pageList = IntStream.rangeClosed(startPage, endPage).boxed().collect(Collectors.toList());
    }

}
