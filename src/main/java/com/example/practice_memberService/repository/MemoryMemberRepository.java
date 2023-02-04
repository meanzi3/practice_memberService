package com.example.practice_memberService.repository;

import com.example.practice_memberService.domain.Member;

import java.util.*;

/**
 * 동시성 문제가 고려되어 있지 않다. 실무에서는 ConcurrentHashMap, AtomicLong 사용을 고려한다.
 */

public class MemoryMemberRepository implements MemberRepository{

  private static Map<Long, Member> store = new HashMap<>();
  private static long sequence = 0L;

  @Override
  public Member save(Member member) {
    member.setId(++sequence);
    store.put(member.getId(), member);
    return member;
  }

  @Override
  public Optional<Member> findById(Long id) {
    return Optional.ofNullable(store.get(id));
    // Null일 경우를 대비하여 Optional 이용
    // 입력받은 id값을 키로 하는 맵의 값(맴베 객체)을 반환
  }

  @Override
  public Optional<Member> findByName(String name) {
    return store.values().stream()
            .filter(member -> member.getName().equals(name))
            .findAny();
    // 입력받은 이름과 같은 맵의 값(맴버 객체)을 필터링하여 모두 반환
  }

  @Override
  public List<Member> findAll() {
    return new ArrayList<>(store.values());
    // 저장된 맵의 모든 값을 arrayList로 반환
  }

  public void clearStore() {store.clear();} // 저장된 맵 모두 비우기
}
