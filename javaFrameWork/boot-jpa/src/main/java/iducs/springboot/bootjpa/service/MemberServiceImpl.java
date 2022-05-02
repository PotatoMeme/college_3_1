package iducs.springboot.bootjpa.service;

import iducs.springboot.bootjpa.domain.Member;
import iducs.springboot.bootjpa.domain.Memo;
import iducs.springboot.bootjpa.entity.MemberEntity;
import iducs.springboot.bootjpa.entity.MemberEntity;
import iducs.springboot.bootjpa.repository.MemberRepository;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Override
    public Member readById(Long seq) {
        Member member = null;
        Optional<MemberEntity> result = memberRepository.findById(seq);
        if(result.isPresent()) {
            member = entityToDto(result.get());
//                    Member.builder()
//                    .seq(result.get().getSeq())
//                    .id(result.get().getId())
//                    .pw(result.get().getPw())
//                    .name(result.get().getName())
//                    .email(result.get().getEmail())
//                    .phone(result.get().getPhone())
//                    .address(result.get().getAddress())
//                    .build();
        }
        /*
        * MemberEntity result = memberRepository.getById(seq);
        * member = entityToDto(result);
        * */
        return member;
    }



    @Override
    public List<Member> readAll() {
        List<Member> members = new ArrayList<Member>();
        List<MemberEntity> entities = memberRepository.findAll();
        for(MemberEntity entity : entities){
            Member member = entityToDto(entity);
            members.add(member);
        }
        return members;
    }

    @Override
    public void update(Member member) {
        MemberEntity entity = dtoToEntity(member);
        memberRepository.save(entity);
    }

    @Override
    public void delete(Member member) {
        MemberEntity entity = dtoToEntity(member);
        //memberRepository.delete(entity);
        memberRepository.deleteById(entity.getSeq());
    }

    @Override
    public Member readByName(Member member) {
        return null;
    }

    @Override
    public Member readByEmail(String email) {
        return null;
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
    private Member entityToDto(MemberEntity entity){
        Member member = Member.builder()
                .seq(entity.getSeq())
                .id(entity.getId())
                .pw(entity.getPw())
                .name(entity.getName())
                .email(entity.getEmail())
                .phone(entity.getPhone())
                .address(entity.getAddress())
                .build();

        return  member;
    }
}
