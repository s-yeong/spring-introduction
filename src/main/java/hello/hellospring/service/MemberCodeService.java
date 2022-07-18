package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberCodeService {

    // 회원 서비스를 만드려면 먼저 회원 리포지토리가 있어야함
     private final MemberRepository memberRepository;

    public MemberCodeService(MemberRepository memberRepository){
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
