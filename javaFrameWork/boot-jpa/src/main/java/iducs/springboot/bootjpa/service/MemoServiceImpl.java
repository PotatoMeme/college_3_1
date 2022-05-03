package iducs.springboot.bootjpa.service;


import iducs.springboot.bootjpa.domain.Member;
import iducs.springboot.bootjpa.domain.Memo;
import iducs.springboot.bootjpa.entity.MemberEntity;
import iducs.springboot.bootjpa.entity.MemoEntity;
import iducs.springboot.bootjpa.repository.MemoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MemoServiceImpl implements MemoService{
    final MemoRepository memoRepository;
    public MemoServiceImpl(MemoRepository memoRepository){
        this.memoRepository = memoRepository;
    }
    @Override
    public void create(Memo memo){
        MemoEntity entity = MemoEntity.builder()
                .mno(memo.getMno())
                .memoText(memo.getMemoText())
                .build();
        memoRepository.save(entity);
    }

    @Override
    public Memo readById(Long mno) {
        Memo memo = null;
        Optional<MemoEntity> result = memoRepository.findById(mno);
        if(result.isPresent()) {
            memo = Memo.builder()
                    .mno(result.get().getMno())
                    .memoText(result.get().getMemoText())
                    .build();
        }
        return memo;

    }
    private MemoEntity dtoToEntity(Memo  memo){
        MemoEntity entity = MemoEntity.builder()
                .mno(memo.getMno())
                .memoText(memo.getMemoText())
                .build();
        return entity;
    }
    private Memo entityToDto(MemoEntity entity){
        Memo memo = Memo.builder()
                .mno(entity.getMno())
                .memoText(entity.getMemoText())
                .build();

        return  memo;
    }

    @Override
    public List<Memo> readAll() {
        List<Memo> memos = new ArrayList<Memo>();
        List<MemoEntity> entities = memoRepository.findAll();
        for (MemoEntity entity : entities) {
            Memo memo = entityToDto(entity);
            memos.add(memo);
        }
        return memos;
    }

    @Override
    public void update(Memo memo) {
        MemoEntity entity = MemoEntity.builder()
                .mno(memo.getMno())
                .memoText(memo.getMemoText())
                .build();
        memoRepository.save(entity);
    }

    @Override
    public void delete(Memo memo) {
        MemoEntity entity = dtoToEntity(memo);
        memoRepository.deleteById(entity.getMno());
    }
}
