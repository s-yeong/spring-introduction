package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
@SpringBootTest
@Transactional  // 데이터베이스는 기본적으로 트랜젝션이라는 개념이 있음
// Test케이스에 달면 테스트를 시작할 때 트랜젝션을 먼저 실행하고 DB에 데이터를 다 넣은 다음 테스트 끝난 후 롤백 해줌
// 테스트 하나하나 다 적용
class MemberServiceIntegrationTest {

    // 테스트케이스는 필드기반으로 autowired로 받는게 편함
    @Autowired
    MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
//    @Commit
    void 회원가입() {

        //given
        Member member = new Member();
        member.setName("spring");

        //when
        Long saveId = memberService.join(member);

        //then
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

        /*try {
            memberService.join(member2);
            fail();
        } catch (IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }*/


    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}