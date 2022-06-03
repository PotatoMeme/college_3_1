package iducs.springboot.bootjpa.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
//부모 클래스는 테이블과 매핑하지 않고 부모 클래스를 상속 받은 자식 클래스에게 매핑 정보만 제공
//실제 테이블과는 매핑되지 않고, 단순히 매핑 정보를 상속할 목적으로 사용
//해당 클래스를 상속받는 엔티티에서 해당 클래스 필드를 컬럼으로 사용
@EntityListeners(value = {AuditingEntityListener.class})
//엔티티를 DB에 적용하기 전, 이후에 커스텀 콜백을 요청할 수 있는 어노테이션
//@EntityListeners의 인자로 커스텀 콜백을 요청할 클래스를 지정
@Getter
public abstract class BaseEntity {
    @CreatedDate // 해당 엔티티가 생성될 때, 생성하는 시각을 자동으로 추가
    @Column(name = "regdate",updatable = false)
    private LocalDateTime regDate;

    @LastModifiedDate
    @Column(name = "moddate")
    private LocalDateTime modDate;

}
