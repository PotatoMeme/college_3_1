package iducs.springboot.bootjpa.service;

import iducs.springboot.bootjpa.domain.Member;


import java.util.List;

public interface MemberService {
    void create(Member member);
    Member readById(Long seq);
    List<Member> readAll();
    void update(Member member);
    void delete(Member member);
    Member readByName(Member member);
    Member readByEmail(String email);

}
