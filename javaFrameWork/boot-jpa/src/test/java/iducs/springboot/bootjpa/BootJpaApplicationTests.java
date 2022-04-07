package iducs.springboot.bootjpa;

import iducs.springboot.bootjpa.entity.MemberEntity;
import iducs.springboot.bootjpa.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.stream.IntStream;

@SpringBootTest
class BootJpaApplicationTests {

    @Autowired
    MemberRepository memberRepository;

    @Test// Unit Test 통합테스트
    void contextLoads() {
        IntStream.rangeClosed(1,10).forEach(i->{
            MemberEntity member =
                    MemberEntity.builder().id("id"+i).
                            pw("pw"+i).name("name"+i).build();
            memberRepository.save(member);
        });
    }

}
