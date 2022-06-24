package iducs.springboot.kshboard.service;

import iducs.springboot.kshboard.domain.Member;
import iducs.springboot.kshboard.domain.PageRequestDTO;
import iducs.springboot.kshboard.domain.PageResultDTO;
import iducs.springboot.kshboard.entity.MemberEntity;

import java.util.List;

public interface MemberService {
    void create(Member member);
    Member readById(Long seq);
    List<Member> readAll();
    PageResultDTO<Member, MemberEntity> readListBy(PageRequestDTO pageRequestDTO);

    void update(Member member);
    void delete(Member member);
    Member readByName(Member member);
    Member readByEmail(String email);

    Member loginByEmail(Member member);
}
