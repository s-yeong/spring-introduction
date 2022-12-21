package com.hello.hellospring.repository;

import com.hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    void save() {

        //given
        Member member = new Member();
        member.setName("홍길동");

        //when
        repository.save(member);

        //then
        Member result = repository.findById(member.getId()).get();
        assertThat(result).isEqualTo(member);
    }

    @Test
    void findByName() {
        //given
        Member member1 = new Member();
        member1.setName("홍길동");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("홍길용");
        repository.save(member2);

        //when
        Member result = repository.findByName("홍길동").get();

        //then
        assertThat(result).isEqualTo(member1);
    }

    @Test
    void findAll() {
        //given
        Member member1 = new Member();
        member1.setName("홍길동");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("홍길용");
        repository.save(member2);

        //when
        List<Member> result = repository.findAll();

        //then
        assertThat(result.size()).isEqualTo(2);
    }

}