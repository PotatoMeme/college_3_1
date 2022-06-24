package iducs.springboot.kshboard;

import iducs.springboot.kshboard.entity.MemberEntity;
import iducs.springboot.kshboard.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;
import java.util.stream.IntStream;

@SpringBootTest
class KshBoardApplicationTests {
    @Autowired
    MemberRepository memberRepository;

    @Test// Unit Test 통합테스트
    void testMemberInitialize() {
        IntStream.rangeClosed(1,50).forEach(i->{
            MemberEntity member =
                    MemberEntity.builder()
                            .id("id" + i)
                            .pw("pw" + i)
                            .name("name-" + i)
                            .email("email" + i + "@induk.ac.kr" )
                            .phone("phone" + new Random().nextInt(50))
                            .address("address" + new Random().nextInt(50))
                            .ban(false)
                            .level("1")
                            .build();
            memberRepository.save(member);
        });
    }
    @Test
    void testAdmin() {
// Integer 데이터 흐름, Lambda 식 - 함수형 언어의 특징을 활용
        String str = "admin";
        MemberEntity entity = MemberEntity.builder()
                .id(str)
                .pw(str)
                .name("name-" + str )
                .email(str + "@induk.ac.kr")
                .phone("phone-" + new Random().nextInt(50))
                .address("address-" + new Random().nextInt(50))
                .ban(false)
                .level("3")
                .build();
        memberRepository.save(entity);
    }
    @Test
    void testMan() {
// Integer 데이터 흐름, Lambda 식 - 함수형 언어의 특징을 활용
        String str = "testMan";
        MemberEntity entity = MemberEntity.builder()
                .id(str)
                .pw(str)
                .name("name-" + str )
                .email(str + "@induk.ac.kr")
                .phone("phone-" + new Random().nextInt(50))
                .address("address-" + new Random().nextInt(50))
                .ban(true)
                .build();
        memberRepository.save(entity);
    }
}
