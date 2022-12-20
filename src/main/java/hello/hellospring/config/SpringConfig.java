package hello.hellospring.config;

import hello.hellospring.repository.JpaMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
public class SpringConfig {
// 스프링이 뜰 때 Configuration읽고 이 것을 스프링 빈에 등록하라는 뜻이네 라고 암

    private EntityManager em;
    @Autowired
    public SpringConfig(EntityManager em){
        this.em = em;
    }
    @Bean
    public MemberService memberCodeService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        // 인터페이스는 new 안됨, 구현체를 넣는거임
        // return new JdbcMemberRepository(dataSource);
//        return new JdbcTemplateMemberRepository(dataSource);
        return new JpaMemberRepository(em);

        // *** 다형성 활용! 인터페이스를 두고 구현체를 바꿔낄 수 있으니까 ***
        // before
        // 1. return new MemoryMemberCodeRepository();
        // 2. return new JdbcMemberRepository(dataSource);

    }
}
