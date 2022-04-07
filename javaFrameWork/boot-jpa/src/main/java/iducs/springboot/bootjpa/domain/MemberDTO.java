package iducs.springboot.bootjpa.domain;


import lombok.*;

import java.util.Objects;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {// 레코드를 객체로
    private long seq;
    private String id;
    private String pw;
    private String name;
    private String email;
    private String phone;
    private String address;

}

