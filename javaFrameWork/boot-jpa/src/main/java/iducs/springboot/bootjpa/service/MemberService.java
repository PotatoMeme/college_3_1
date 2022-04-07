package iducs.springboot.bootjpa.service;

import iducs.springboot.bootjpa.entity.MemberEntity;

import java.util.Optional;
import java.util.List;

public interface MemberService {
    int create(MemberEntity member);
    Optional<MemberEntity> readById(Long seq);
    Optional<MemberEntity> readId(String id);
    List<MemberEntity> readAll();
    int update(MemberEntity member);
    int delete(MemberEntity member);
}
