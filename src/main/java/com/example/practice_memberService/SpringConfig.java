package com.example.practice_memberService;

import com.example.practice_memberService.repository.MemberRepository;
import com.example.practice_memberService.repository.MemoryMemberRepository;
import com.example.practice_memberService.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

  @Bean
  public MemberService memberService(){
    return new MemberService(memberRepository());
  }

  @Bean
  public MemberRepository memberRepository(){
    return new MemoryMemberRepository();
  }
}
