package iducs.springboot.bootjpa.service;

import iducs.springboot.bootjpa.entity.MemberEntity;
import iducs.springboot.bootjpa.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService{

    @Autowired
    MemberRepository memberRepository; // DI(Dependency Injection)
    // MemberRepository memberRepository =  new MemberRepositoryImpl();
    /**/
    //public MemberServiceImpl()
    @Override
    public int create(MemberEntity member) {
        return 0;
    }
    @Override
    public Optional<MemberEntity> readById(Long seq){
        return memberRepository.findById(seq);
    }
    @Override
    public Optional<MemberEntity> readId(String id){
        return Optional.empty();
    }
    @Override
    public List<MemberEntity> readAll(){
        return memberRepository.findAll();
    }
    @Override
    public int update(MemberEntity member) {
        return 0;
    }
    @Override
    public int delete(MemberEntity member) {
        return 0;
    }
}
