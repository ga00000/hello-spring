package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member); //회원저장하면 저장된 회원 반환
    Optional<Member> findById(Long id); //자바8기능,값없을때 null 대신 반환
    Optional<Member> findByName(String name);
    List<Member> findAll(); //저장된 전체회원 리스트 반환환

}
