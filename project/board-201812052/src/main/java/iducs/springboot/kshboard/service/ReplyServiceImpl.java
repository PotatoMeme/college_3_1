package iducs.springboot.kshboard.service;

import iducs.springboot.kshboard.domain.Member;
import iducs.springboot.kshboard.domain.Reply;
import iducs.springboot.kshboard.entity.BoardEntity;
import iducs.springboot.kshboard.entity.MemberEntity;
import iducs.springboot.kshboard.entity.ReplyEntity;
import iducs.springboot.kshboard.repository.ReplyRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class ReplyServiceImpl implements ReplyService {
    private final ReplyRepository replyRepository;

    public ReplyServiceImpl(ReplyRepository replyRepository) {
        this.replyRepository = replyRepository;
    }

    @Override
    public List<Reply> getRepliesWithBno(Long bno) {
        Object[] result = replyRepository.getReplyWithBno(bno);
        List<Reply> listOfMemo = new ArrayList<>();
        Object[] indexes;
        for (Object index : result) {
            indexes = (Object[]) index;
            listOfMemo.add(entityToDto((ReplyEntity) indexes[0], (MemberEntity) indexes[1], (BoardEntity) indexes[2]));
        }
        return listOfMemo;
    }

    @Override
    public boolean deleteConstraints(Long bno) {
        replyRepository.deleteByBno(bno);
        return true;
    }

    @Override
    public boolean deleteOne(Long rno) {
        replyRepository.deleteById(rno);
        return true;
    }

    @Override
    public void register(Reply reply, Member dto, Long bno) {
        log.info("board register : " + dto);
        reply.setWriterSeq(dto.getSeq());
        reply.setBoardBno(bno);
        ReplyEntity replyEntity = dtoToEntity(reply);
        replyRepository.save(replyEntity);
    }

    @Override
    public Reply getReply(Long rno) {
        Reply reply = null;
        Optional<ReplyEntity> result = replyRepository.findById(rno);
        if (result.isPresent()) {
            log.info("result.get().getWriter():"+result.get().getWriter());
            reply = entityToDto(result.get(), result.get().getWriter(), result.get().getBoard());
        }
        log.info("reply:"+reply);
        return reply;
    }

    @Override
    public void update(Reply reply, Member dto, Long bno) {
        log.info("board update : " + dto);
        reply.setWriterSeq(dto.getSeq());
        reply.setBoardBno(bno);
        ReplyEntity replyEntity = dtoToEntity(reply);
        replyRepository.save(replyEntity);
    }


}
