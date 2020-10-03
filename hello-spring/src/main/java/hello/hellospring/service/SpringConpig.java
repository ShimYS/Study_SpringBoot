package hello.hellospring.service;

import hello.hellospring.repository.JdbcMemberRepository;
import hello.hellospring.repository.JdbcTemplateMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConpig {

    private DataSource dataSource;

    @Autowired
    public SpringConpig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository () {
        return new JdbcTemplateMemberRepository(dataSource);  // JdbcTemplate를 활용한 데이터 저장
//      return new JdbcMemberRepository(dataSource);          // 순수Jdbc를 활용한 데이터 저장
//      return new MemoryMemberRepository();                  // 메모리에 데이터 저장
    }
}
