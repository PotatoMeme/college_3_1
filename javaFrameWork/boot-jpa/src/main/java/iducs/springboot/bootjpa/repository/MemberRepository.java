package iducs.springboot.bootjpa.repository;

import iducs.springboot.bootjpa.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity,Long> {

}
