package iducs.springboot.bootjpa.repository;

import iducs.springboot.bootjpa.entity.MemoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemoRepository  extends JpaRepository<MemoEntity,Long> {
    // SpringDataJPA 활용하여 데이터 액세스를 담당하는 객체를 생성하는데 활용
}
