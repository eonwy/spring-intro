package kumaru.spring_beginning;

import kumaru.spring_beginning.repository.JdbcMemberRepository;
import kumaru.spring_beginning.repository.JdbcTemplateMemberRepository;
import kumaru.spring_beginning.repository.MemberRepository;
import kumaru.spring_beginning.repository.MemoryMemberRepository;
import kumaru.spring_beginning.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

//    @Autowired  DataSource dataSource;

    private  DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//        return new JdbcMemberRepository(dataSource);
        return new JdbcTemplateMemberRepository(dataSource);
    }
}
