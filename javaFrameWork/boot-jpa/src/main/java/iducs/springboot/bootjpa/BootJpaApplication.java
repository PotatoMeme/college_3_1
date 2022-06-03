package iducs.springboot.bootjpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.filter.HiddenHttpMethodFilter;

@SpringBootApplication
@EnableJpaAuditing// JPA에서 AuditingEntityListener를 활성화하는 애노테이션
// AuditingEntityListener : JPA에서 AuditingEntityListener를 활성화하는 애노테이션
public class BootJpaApplication {
    public static void main(String[] args) {SpringApplication.run(BootJpaApplication.class, args);}
    @Bean
    public HiddenHttpMethodFilter hiddenHttpMethodFilter(){
        return new HiddenHttpMethodFilter();
    }
}
