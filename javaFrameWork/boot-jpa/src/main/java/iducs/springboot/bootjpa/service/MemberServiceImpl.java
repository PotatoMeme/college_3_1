package iducs.springboot.bootjpa.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import iducs.springboot.bootjpa.domain.Member;

import iducs.springboot.bootjpa.domain.Memo;
import iducs.springboot.bootjpa.domain.PageRequestDTO;
import iducs.springboot.bootjpa.domain.PageResultDTO;
import iducs.springboot.bootjpa.entity.MemberEntity;
import iducs.springboot.bootjpa.entity.QMemberEntity;

import iducs.springboot.bootjpa.repository.MemberRepository;

import lombok.extern.java.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

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
    public PageResultDTO<Member, MemberEntity> readListBy(PageRequestDTO pageRequestDTO) {
        Pageable pageable;
        System.out.println(pageRequestDTO.getSort());
        if(pageRequestDTO.getSort()==null){
            pageable = pageRequestDTO.getPageable(Sort.by("seq").ascending());
        }else if(pageRequestDTO.getSort()){
            pageable = pageRequestDTO.getPageable(Sort.by("seq").descending());
        }else{
            pageable = pageRequestDTO.getPageable(Sort.by("seq").ascending());
        }


        BooleanBuilder booleanBuilder = findByCondition(pageRequestDTO);
        Page<MemberEntity> result = memberRepository.findAll(booleanBuilder, pageable);

        //Page<MemberEntity> result = memberRepository.findAll(pageable);
        Function<MemberEntity, Member> fn = (entity -> entityToDto(entity));
        return new PageResultDTO<>(result, fn);
    }

    private BooleanBuilder findByCondition(PageRequestDTO pageRequestDTO) {
        String type = pageRequestDTO.getType();
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        QMemberEntity qMemberEntity = QMemberEntity.memberEntity;

        BooleanExpression expression = qMemberEntity.seq.gt(0L); // where seq > 0 and title == "ti"
        booleanBuilder.and(expression);

        if(type == null || type.trim().length() == 0) {
            return booleanBuilder;
        }

        String keyword = pageRequestDTO.getKeyword();

        BooleanBuilder conditionBuilder = new BooleanBuilder();
        if(type.contains("e")) // email??? ??????
            conditionBuilder.or(qMemberEntity.email.contains(keyword));
        if(type.contains("p")) // phone??? ??????
            conditionBuilder.or(qMemberEntity.phone.contains(keyword));
        if(type.contains("a")) // address??? ??????
            conditionBuilder.or(qMemberEntity.address.contains(keyword));
        booleanBuilder.and(conditionBuilder);
        return booleanBuilder; // ????????? ?????? or ??????(predicate)
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
