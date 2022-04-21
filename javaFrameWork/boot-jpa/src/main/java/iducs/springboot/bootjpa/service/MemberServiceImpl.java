package iducs.springboot.bootjpa.service;

import iducs.springboot.bootjpa.domain.Member;
import iducs.springboot.bootjpa.entity.MemberEntity;
import iducs.springboot.bootjpa.entity.MemberEntity;
import iducs.springboot.bootjpa.repository.MemberRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService{
    final MemberRepository memberRepository;
    public MemberServiceImpl(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    @Override
    public void create(Member member) {
        MemberEntity entity = dtoToEntity(member);
        memberRepository.save(entity);
    }
    private MemberEntity dtoToEntity(Member member){
        MemberEntity entity = MemberEntity.builder()
                .seq(member.getSeq())
                .id(member.getId())
                .pw(member.getPw())
                .name(member.getName())
                .email(member.getEmail())
                .phone(member.getPhone())
                .address(member.getAddress())
                .build();
        return entity;
    }
    @Override
    public Member readById(Long seq) {
        Member member = null;
        Optional<MemberEntity> result = memberRepository.findById(seq);
        if(result.isPresent()) {
            member = Member.builder()
                    .seq(member.getSeq())
                    .id(member.getId())
                    .pw(member.getPw())
                    .name(member.getName())
                    .email(member.getEmail())
                    .phone(member.getPhone())
                    .address(member.getAddress())
                    .build();
        }
        return member;
    }



    @Override
    public List<Member> readAll() {
        return null;
    }

    @Override
    public void update(Member member) {

    }

    @Override
    public void delete(Member member) {

    }

    @Override
    public Member readByName(Member member) {
        return null;
    }

    @Override
    public Member readByEmail(String email) {
        return null;
    }
}
