package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {
    // 구현체를 자동으로 만들어줌, 스프링 데이터 JPA가 스프링빈에 자동으로 등록
    @Override
    Optional<Member> findByName(String name);
    // JPQL : select m from Member m where m.name = ?

    // 인터페이스 이름만으로도 개발이 끝남!- 메서드 이름만으로 조회 기능 제공, 페이징 기능 자동 제공
    // ex) findByNameAndId
}
