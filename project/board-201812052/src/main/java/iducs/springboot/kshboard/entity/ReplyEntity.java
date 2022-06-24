package iducs.springboot.kshboard.entity;

import lombok.*;

import javax.persistence.*;

@Entity // Spring Data JPA의 엔티티(entity, 개체)임을 나타냄
@Table(name="tb201812052_reply")
// Lombok 관련 Annotations
@ToString(exclude = "board") // toString() 생성
@Getter  // getter() 생성
@Builder
@AllArgsConstructor // 모든 매개변수를 갖는 생성자
@NoArgsConstructor  // 디폴트 생성자(아무런 매개변수가 없는)
public class ReplyEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;

    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    private MemberEntity writer;

    @ManyToOne(fetch = FetchType.LAZY)
    private BoardEntity board;
}
