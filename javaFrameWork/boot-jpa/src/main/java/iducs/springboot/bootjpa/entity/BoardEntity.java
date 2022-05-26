package iducs.springboot.bootjpa.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "tbl_board")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "writer")
public class BoardEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno;

    private String title;
    private String content;

    @ManyToOne//n:1로 조인
    private MemberEntity writer;
}

