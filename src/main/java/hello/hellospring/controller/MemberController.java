package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/** 스프링 빈을 등록하는 2가지 방법
 *  1. 컴포넌트(Component) 스캔과 자동 의존관계 설정
 *  => 스프링이 올라올 떄 컴포넌트 관련된 애노테이션 이 있으면 객체를 생성해서 스프링 컨테이너에 등록을함
 *  2. 자바 코드로 직접 스프링 빈 등록하기
 *  => MemoryMemberCodeRepository, MemberCodeService, SpringConfig
 */
@Controller // Spirng이 뜰 때 MemberController라는 객체를 생성해서 Spring이 들고 있음
// -> Spring 컨테이너에서 Spring Bean이 관리된다.
public class MemberController {

    private final MemberService memberService;
    // 스프링이 관리를하게되면 다 스프링 컨테이너에 등록이되고, 스프링 컨테이너에서 받아서 쓰도록 바꿔야함
    // -> 스프링 컨테이너에 등록


    @Autowired // memberService를 스프링이 스프링 컨테이너에 있는 memberService를 가져다 연결시켜줌
    // -> memberController - memberService 연관관계를 연결해줌
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
