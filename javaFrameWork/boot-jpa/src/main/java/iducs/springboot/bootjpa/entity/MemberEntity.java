package iducs.springboot.bootjpa.entity;

import lombok.*;
import javax.persistence.*;
// Spring Data 관련 Annotations
@Entity // Spring Data JPA의 엔티티(entity, 개체)임을 나타냄
@Table(name="tbl_member")
// Lombok 관련 Annotations
@ToString // toString() 생성
@Getter  // getter() 생성
@Setter // setter() 추가
@Builder
@AllArgsConstructor // 모든 매개변수를 갖는 생성자
@NoArgsConstructor  // 디폴트 생성자(아무런 매개변수가 없는)
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // MySQL, MariaDB Auto Increment
    private Long seq;
    @Column(length=30, nullable=false)
    private String id;
    @Column(length=30, nullable=false)
    private String pw;
    @Column(length=30, nullable=false)
    private String name;
    @Column(length=50, nullable=false)
    private String email;
    @Column(length=30, nullable = true)
    private String phone;
    @Column(length=100)
    private String address;

}
