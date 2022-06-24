package iducs.springboot.kshboard.repository;

import iducs.springboot.kshboard.entity.ReplyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReplyRepository extends
        JpaRepository<ReplyEntity, Long> {
    @Query("select r,m,b from ReplyEntity r left join MemberEntity m on m.seq = r.writer.seq left join r.board b where b.bno =:bno")
    Object[] getReplyWithBno(@Param("bno") Long bno);

    @Modifying
    @Query("delete from ReplyEntity r where r.board.bno = :bno")
    void deleteByBno(@Param("bno") Long bno);
}
