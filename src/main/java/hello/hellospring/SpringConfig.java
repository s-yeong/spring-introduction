package hello.hellospring;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberCodeRepository;
import hello.hellospring.service.MemberCodeService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
// 스프링이 뜰 때 Configuration읽고 이 것을 스프링 빈에 등록하라는 뜻이네 라고 암
    @Bean
    public MemberCodeService memberCodeService(){
        return new MemberCodeService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberCodeRepository();
        // 인터페이스는 new 안됨, 구현체를 넣는거임
    }
}
