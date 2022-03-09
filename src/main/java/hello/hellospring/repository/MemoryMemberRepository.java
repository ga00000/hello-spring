package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member); //저장
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
        // null 반환--> Optional로 감싸서 반환
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream() //자바8 루프 돌리기
                .filter(member -> member.getName().equals(name)) //멤버에서 파라미터로 넘어온 name같은지 확인
                .findAny(); //찾으면 반환--Optional로 반환
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); // 저장된 member 전체 반환
    }
    public void clearStore(){
        store.clear(); //비우기
    }
}
