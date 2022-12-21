package com.hello.hellospring.config;


import com.hello.hellospring.repository.JdbcMemberRepository;
import com.hello.hellospring.repository.JdbcTemplateMemberRepository;
import com.hello.hellospring.repository.JpaMemberRepository;
import com.hello.hellospring.repository.MemberRepository;
import com.hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {

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

    MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService() {
//        return new MemberService(memberRepository());
        return new MemberService(memberRepository);
    }

    // before
    // 1. return new MemoryMemberRepository();
    // 2. return new JdbcMemberRepository(dataSource);
    // 3. return new JdbcTemplateMemberRepository(dataSource);
    // 4.
    /*@Bean
    public MemberRepository memberRepository() {
        return new JpaMemberRepository(em);
    }*/


}
