package iducs.springboot.bootjpa.domain;


import lombok.*;
//애노 테이션을 활용하여 boilerplate code 작성을 줄여줄 목적으로 설께된 프로잭트
// -> @Getter
import java.util.Objects;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {// 레코드를 객체로
    //Data  Transper object
    private Long seq;
    private String id;
    private String pw;
    private String name;
    private String email;
    private String phone;
    private String address;

}

