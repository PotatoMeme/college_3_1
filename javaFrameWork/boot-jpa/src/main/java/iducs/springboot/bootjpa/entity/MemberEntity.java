package iducs.springboot.bootjpa.entity;

import lombok.*;

import javax.persistence.*;
@Entity
@Table(name = "tbl_member")
@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @Column(length = 30, nullable = false)
    private String id;

    @Column(length = 30, nullable = false)
    private String pw;

    @Column(length = 30, nullable = false)
    private String name;

    @Column(length = 50, nullable = false)
    private String email;

    @Column(length = 30, nullable = true)
    private String phone;

    @Column(length = 100)
    private String address;

}
