package iducs.springboot.kshboard.domain;


import lombok.*;

import java.time.LocalDateTime;

@Data // @Getter,@Setter,@EqualsAndHash,@RequiredArgsConstructor 합친 기능
@Builder //
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Board {
    private Long bno;
    private String title;
    private String content;// BoardEntity
    private LocalDateTime regDate; // <- 게시글 등록
    private LocalDateTime modDate; // <- 게시글 수정
    private Boolean ban;
    private Long views;

    private Long writerSeq; // MemberEntity
    private String writerId;
    private String writerName;
    private String writerEmail;
    private Boolean writerBan;

    private int replyCount; // 추가적인 필드 : 게시물이 댓글수

}
