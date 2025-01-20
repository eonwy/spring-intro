package kumaru.spring_beginning.service;

import kumaru.spring_beginning.domain.Member;
import kumaru.spring_beginning.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memoryMemberRepository;

    @Test
    void 회원가입() {
        // given
        Member member = new Member();
        member.setName("Hello");

        // when
        Long savedId = memberService.join(member);

        // then
        Member found = memberService.findOne(savedId).get();
        assertThat(member.getName()).isEqualTo(found.getName());
    }

    @Test
    public void 중복_회원_예외() {
        // given
        Member member1 = new Member();
        member1.setName("Hello");

        Member member2 = new Member();
        member2.setName("Hello");

        memberService.join(member1);
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(exception.getMessage()).isEqualTo("이미 존재하는 회원입니다");

    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}