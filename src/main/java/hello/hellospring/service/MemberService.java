package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
//@Service    // Spring이 올라 올 때, Service네 하고 스프링 컨테이너에 MemberService를 등록해줌
public class MemberService {

    // 회원 서비스를 만드려면 먼저 회원 리포지토리가 있어야함
    private final MemberRepository memberRepository;

    /**
     * memberRepository를 직접 new로 생성하는게 아니라 외부에서 넣어주도록 바꿈!
     */

    // BEFORE
    // private final MemberRepository memberRepository = new MemoryMemberRepository();

    // -> AFTER
    @Autowired
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }   // MemberService입장에서, 외부에서 memberRepository를 넣어주기 때문에 의존성 주입이라고함


    /**
     * 회원 가입
     */
    public long join(Member member){
        // 같은 이름이 있는 중복 회원X
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
            // ifPresent는 null이 아닌 값이 있으면 동작한다는 것
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }

}
