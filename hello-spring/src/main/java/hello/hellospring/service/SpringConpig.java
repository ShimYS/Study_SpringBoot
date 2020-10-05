package hello.hellospring.service;

import hello.hellospring.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConpig {

    /* 순수Jdbc, JdbcTemplate를 사용할 때 주입해준다. */
//    private DataSource dataSource;
//
//    @Autowired
//    public SpringConpig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }

    /* JPA를 사용할 때 주입해준다. */

//    private EntityManager em;
//
//    @Autowired
//    public SpringConpig(EntityManager em) {
//        this.em = em;
//    }

    /* 스프링 데이터 JPA를 사용할 때 주입해준다. */
    private final MemberRepository memberRepository;

    @Autowired
    public SpringConpig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

    /* 스프링 데이터 JPA를 사용할 때는 사용하지 않는다. */
//    @Bean
//    public MemberRepository memberRepository () {
////      return new JpaMemberRepository(em);                   // JPA를 활용한 데이터 저장
////      return new JdbcTemplateMemberRepository(dataSource);  // JdbcTemplate를 활용한 데이터 저장
////      return new JdbcMemberRepository(dataSource);          // 순수Jdbc를 활용한 데이터 저장
////      return new MemoryMemberRepository();                  // 메모리에 데이터 저장
//    }
}
