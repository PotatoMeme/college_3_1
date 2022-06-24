package iducs.springboot.kshboard.domain;


import lombok.Builder;
import lombok.Data;

@Data // @Getter,@Setter,@EqualsAndHash,@RequiredArgsConstructor 합친 기능
@Builder //
public class Member {
    private Long seq;
    private String id;
    private String pw;
    private String name;
    private String email;
    private String phone;
    private String address;
    private Boolean ban;
    private String level;
}
