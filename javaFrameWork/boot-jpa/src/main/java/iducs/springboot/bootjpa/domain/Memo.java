package iducs.springboot.bootjpa.domain;


import lombok.Builder;
import lombok.Data;

@Data // @Getter,@Setter,@EqualsAndHash,@RequiredArgsConstructor 합친 기능
@Builder //
public class Memo { //dto
    private Long mno;
    private String memoText;
}
