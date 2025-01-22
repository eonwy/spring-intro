package kumaru.spring_beginning.service;

import kumaru.spring_beginning.domain.Member;
import kumaru.spring_beginning.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memoryMemberRepository;

    @BeforeEach
    public void beforeEach() {
        memoryMemberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memoryMemberRepository);
    }

    @AfterEach
    public void afterEach() {
         memoryMemberRepository.clearStore();
    }

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

        // when
//        memberService.join(member1);
//        assertThrows(IllegalStateException.class, () -> memberService.join(member2));


        memberService.join(member1);
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(exception.getMessage()).isEqualTo("이미 존재하는 회원입니다");

        //        try {
//            memberService.join(member2);
//            fail();
//        } catch (IllegalStateException e) {
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");
//        }

        // then

    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}