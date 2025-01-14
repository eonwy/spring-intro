package kumaru.spring_beginning;

import kumaru.spring_beginning.repository.MemberRepository;
import kumaru.spring_beginning.repository.MemoryMemberRepository;
import kumaru.spring_beginning.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
