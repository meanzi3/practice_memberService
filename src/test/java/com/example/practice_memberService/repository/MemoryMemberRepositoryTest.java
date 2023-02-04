package com.example.practice_memberService.repository;

import com.example.practice_memberService.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemoryMemberRepositoryTest {

  MemoryMemberRepository repository = new MemoryMemberRepository();

  // 각 테스트가 종료될 때 마다 이 기능을 실행한다. (메모리 DB에 저장된 데이터를 삭제한다.)
  @AfterEach
  public void afterEach(){
    repository.clearStore();
  }

  @DisplayName("회원 저장 테스트")
  @Test
  public void save() {
    // given
    Member member = new Member();
    member.setName("spring");

    // when
    repository.save(member);

    // then
    Member result = repository.findById(member.getId()).get();
    assertThat(result).isEqualTo(member);
  }

  @DisplayName("회원 이름으로 조회 테스트")
  @Test
  void findByName() {
    // given
    Member member1 = new Member();
    member1.setName("spring1");
    repository.save(member1);

    Member member2 = new Member();
    member2.setName("spring2");
    repository.save(member2);

    // when
    Member result = repository.findByName("spring1").get(); // 리포지토리에서 spring1이라는 이름을 가진 저장소를 찾아 result에 저장

    // then
    assertThat(result).isEqualTo(member1); // result가 member1과 같은지 확인
  }

  @DisplayName("모든 회원 조회 테스트")
  @Test
  void findAll() {
    // given
    Member member1 = new Member();
    member1.setName("spring1");
    repository.save(member1);

    Member member2 = new Member();
    member2.setName("spring2");
    repository.save(member2);

    // when
    List<Member> result = repository.findAll(); // findAll 하여 reault 리스트에 저장

    // then
    assertThat(result.size()).isEqualTo(2); // result의 크기가 2인지 확인
  }
}