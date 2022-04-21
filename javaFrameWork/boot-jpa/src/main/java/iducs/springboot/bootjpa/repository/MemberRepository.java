package iducs.springboot.bootjpa.repository;

import iducs.springboot.bootjpa.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberEntity,Long> {
    // CR(EAD)R(EADALL)UD
    // JDBC 프로그래밍 순서
    // 사용할 자원 선언(Connection 선언, Statement 선언, ResultSet 선언)
    // Connection 생성 - 연결 설정 (application.properties)
    // Statement 생성 (Statement, PreparedStatement, CallableStatement)
    // ResultSet 생성 (entity - entities : records)
    // ResultSet -> DTO로 변환후 반환 / 영향받은 record 수를 반환
    // (List<DTO> - readList, DTO - readOne, int (row 수) - create, update, delete)
}
