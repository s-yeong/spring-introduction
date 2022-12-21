package com.hello.hellospring.service;

import com.hello.hellospring.domain.Member;
import com.hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.assertThrows;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {

        //given
        Member member = new Member();
        member.setName("홍길동");

        //when
        Long memberId = memberService.join(member);

        //then
        Member findMember = memberRepository.findById(memberId).get();
        assertThat(findMember.getName()).isEqualTo(member.getName());
    }

    @Test
    void 중복_회원_예외() {

        //given
        Member member1 = new Member();
        member1.setName("홍길동");

        Member member2 = new Member();
        member2.setName("홍길동");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }

}