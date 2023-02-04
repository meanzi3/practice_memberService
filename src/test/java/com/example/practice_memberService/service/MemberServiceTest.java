package com.example.practice_memberService.service;

import com.example.practice_memberService.domain.Member;
import com.example.practice_memberService.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

  MemberService memberService;
  MemoryMemberRepository memberRepository;

  @BeforeEach
  public void beforeEach(){
    memberRepository = new MemoryMemberRepository();
    memberService = new MemberService(memberRepository);
  }

  @AfterEach
  public void afterEach(){
    memberRepository.clearStore();
  }

  @DisplayName("회원 가입 테스트")
  @Test
  void join() {
    // given
    Member member = new Member();
    member.setName("hello");

    // when
    Long savedId = memberService.join(member);

    // then
    Member findMember = memberService.findOne(savedId).get();
    assertThat(member.getName()).isEqualTo(findMember.getName());
  }

  @DisplayName("중복 회원 예외 테스트")
  @Test
  void duplicatedMember(){
    // given
    Member member1 = new Member();
    member1.setName("spring");

    Member member2 = new Member();
    member2.setName("spring");

    // when
    memberService.join(member1);
    IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> memberService.join(member2));
    assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
  }

  @DisplayName("전체 회원 조회 테스트")
  @Test
  void findMembers() {
    // given
    // when
    // then
  }

  @DisplayName("특정 회원 조회 테스트")
  @Test
  void findOne() {
    // given
    // when
    // then
  }
}
