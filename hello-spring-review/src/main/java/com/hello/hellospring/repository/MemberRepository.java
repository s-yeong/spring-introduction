package com.hello.hellospring.repository;

import com.hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    // 회원 저장 - name을 가져오는게 아니라 Member 객체를 가져와서 Member 객체 반환
    Member save(Member memebr);

    // 회원 조회
    Optional<Member> findById(Long id);

    // 이름으로 회원 조회
    Optional<Member> findByName(String name);

    // 회원 목록 조회
    List<Member> findAll();

}
