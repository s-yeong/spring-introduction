package hello.hellospring;

import hello.hellospring.repository.JdbcMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.service.MemberCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {
// 스프링이 뜰 때 Configuration읽고 이 것을 스프링 빈에 등록하라는 뜻이네 라고 암

    private DataSource dataSource;
    @Autowired
    public SpringConfig(DataSource dataSource){
        this.dataSource = dataSource;
    }
    @Bean
    public MemberCodeService memberCodeService(){
        return new MemberCodeService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        //return new MemoryMemberCodeRepository();
        // 인터페이스는 new 안됨, 구현체를 넣는거임
        return new JdbcMemberRepository(dataSource);
    }
}
