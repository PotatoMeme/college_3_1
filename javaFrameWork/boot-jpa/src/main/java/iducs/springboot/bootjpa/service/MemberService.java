package iducs.springboot.bootjpa.service;

import iducs.springboot.bootjpa.domain.Member;
import iducs.springboot.bootjpa.domain.PageRequestDTO;
import iducs.springboot.bootjpa.domain.PageResultDTO;
import iducs.springboot.bootjpa.entity.MemberEntity;


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

}
