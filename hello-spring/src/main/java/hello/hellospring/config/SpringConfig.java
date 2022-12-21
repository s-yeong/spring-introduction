package hello.hellospring.config;

import hello.hellospring.aop.TimeTraceAop;
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


    // before - JDBC
    // private final DataSource dataSource;
    /*public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }*/

    // before - JPA
    /*private EntityManager em;
    @Autowired
    public SpringConfig(EntityManager em){
        this.em = em;
    }*/

    // after - Data JPA
    private MemberRepository memberRepository;

    // 스프링 데이터 JPA가 구현체를 만들어 놓은게 등록된다!
    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService(){
        // before
//        return new MemberService(memberRepository());

        // after - Data Jpa
        return new MemberService(memberRepository);
    }

    // befroe - Jpa까지
//    @Bean
//    public MemberRepository memberRepository(){
        // 인터페이스는 new 안됨, 구현체를 넣는거임

        // *** 다형성 활용! 인터페이스를 두고 구현체를 바꿔낄 수 있으니까 ***
        // before
        // 1. return new MemoryMemberCodeRepository();
        // 2. return new JdbcMemberRepository(dataSource);
        // 3. return new JdbcTemplateMemberRepository(dataSource);
        // 4. return new JpaMemberRepository(em);
//    }


    // Aop는 특별하기 때문에 인지하기 위해 빈 등록
    /*@Bean
    public TimeTraceAop timeTraceAop() {
        return new TimeTraceAop();
    }*/
}
