package iducs.springboot.bootjpa.entity;

import lombok.*;

import lombok.*;

import javax.persistence.*;

@Entity // Spring Data JPA의 엔티티(entity, 개체)임을 나타냄
@Table(name="tbl_memo")

@ToString // toString() 생성
@Getter  // getter() 생성
@Setter // setter() 추가
@Builder
@AllArgsConstructor // 모든 매개변수를 갖는 생성자
@NoArgsConstructor  // 디폴트 생성자(아무런 매개변수가 없는)
public class MemoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mno;

    @Column(length=50, nullable=false)
    private String memoText;

}
