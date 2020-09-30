package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;   // alt + enter
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void join() {
        // given 무언가 주워졌는데
        Member member = new Member();
        member.setName("hello");

        // when 이걸 실행했을때
        Long saveId = memberService.join(member);

        // then 결과는 이렇게 나와야해
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        // given
        Member member1 = new Member();
        member1.setName("hello1");

        Member member2 = new Member();
        member2.setName("hello1");

        // when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));// 뒤 로직을 실행시 앞 예외가 발생하는지 체크
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");// 메세지를 검증

//         try {
//            memberService.join(member2);
//            fail();
//         } catch (IllegalStateException e) {
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//         }

         // then
    }

    @Test
    void findMembers() {
        // given
        Member member1 = new Member();
        member1.setName("hello1");
        memberService.join(member1);

        Member member2 = new Member();
        member2.setName("hello2");
        memberService.join(member2);
        // when
        List<Member> result = memberService.findMembers();

        // then
        assertThat(result.size()).isEqualTo(2);
    }

    @Test
    void findOne() {
    }
}