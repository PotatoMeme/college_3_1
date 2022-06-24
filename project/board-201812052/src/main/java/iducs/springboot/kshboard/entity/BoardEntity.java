package iducs.springboot.kshboard.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "board201812052")
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
    private Boolean ban;
    private Long views;

    @ManyToOne(fetch = FetchType.LAZY) // 지연 로딩
    private MemberEntity writer; // 연관관계 지정 or N:1 관계성 연결 : left join

    public void changeTitle(String title) {
        this.title = title;
    }
    public void changeContent(String content) {
        this.content = content;
    }

    public void addViews() {
        this.views += 1;
    }
}
