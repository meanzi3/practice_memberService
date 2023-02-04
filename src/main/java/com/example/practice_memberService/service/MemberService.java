package com.example.practice_memberService.service;

import com.example.practice_memberService.domain.Member;
import com.example.practice_memberService.repository.MemberRepository;
import com.example.practice_memberService.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {
  private final MemberRepository memberRepository;

  public MemberService(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }

  /**
   * 회원 가입
   */

  public Long join(Member member){
    // 중복 회원 검증
    validateDuplicateMember(member);

    memberRepository.save(member);
    return member.getId();
  }

  private void validateDuplicateMember(Member member) {
    memberRepository.findByName(member.getName())
            .ifPresent(m -> {
              throw new IllegalArgumentException("이미 존재하는 회원입니다.");
            });
  }

  /**
   * 전체 회원 조회
   */
  public List<Member> findMembers() {
    return memberRepository.findAll();
  }

  /**
   * 특정 회원 조회
   */
  public Optional<Member> findOne(Long memberId){
    return memberRepository.findById(memberId);
  }

}
