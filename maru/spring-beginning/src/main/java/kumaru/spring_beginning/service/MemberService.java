package kumaru.spring_beginning.service;

import kumaru.spring_beginning.domain.Member;
import kumaru.spring_beginning.repository.MemberRepository;
import kumaru.spring_beginning.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    private final MemberRepository memberRepository;

    /**
     * 회원 가입
     */
    public Long join(Member member) {
//이름 중복 회원 x
//        Optional<Member> result = memberRepository.findByName(member.getName());
////        optional과 함께 사용.
////        result.get() 으로 바로 꺼낼 수도 있음 (권장 x)
////        result.orElseGet() 같은 메서드도
//        result.ifPresent((m-> {
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//        }));

//        ctrl + T => 리팩토링 가능
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName()).ifPresent((m-> {
            throw new IllegalStateException("이미 존재하는 회원입니다");
        }));
    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long id) {
        return memberRepository.findById(id);
    }
}
