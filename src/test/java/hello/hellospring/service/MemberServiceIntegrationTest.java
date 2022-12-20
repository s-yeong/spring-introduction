package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
@SpringBootTest // 스프링 컨테이너와 테스트를 함꼐 실행
@Transactional
// 데이터베이스는 기본적으로 트랜젝션이라는 개념이 있음
// Test 케이스에 달면 테스트를 시작할 때 트랜젝션을 먼저 실행하고 DB에 데이터를 다 넣은 다음 테스트 끝난 후 "롤백" 해줌
// 테스트 하나하나 다 적용(다음 텥스트에 영향X)
class MemberServiceIntegrationTest {    // 스프링 통합 테스트 <-> 단위 테스트

    // 테스트케이스는 필드기반 주입이 편함
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    // 직접 객체 생성이 아닌 스프링 컨테이너한테 MemberRepository 내놔! 하는거임

    @Test
//    @Commit 하면 DB에 저장
    void 회원가입() {

        //given
        Member member = new Member();
        member.setName("spring");

        //when
        Long saveId = memberService.join(member);

        //then - 저장되어 있는거 확인
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());

    }

    // 중복 회원 검증도 잘 파악해야함!!
    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");;

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

    }
}