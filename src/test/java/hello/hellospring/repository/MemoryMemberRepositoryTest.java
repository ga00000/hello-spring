package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

public class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach //메서드 끝날때마다 실행 callback 함수
    public void afterEach(){ //테스트 전체실행하면 순서 랜덤 실행 -->순서 의존관계x
        repository.clearStore(); //--> 테스트 후 Clear 필요!!
    }

    @Test // junit
    public void save() {
        Member member = new Member();
        member.setName("spring");
        repository.save(member); // repository에 멤버 저장
        Member result = repository.findById(member.getId()).get();
        // findById로 저장됐는지 검증 + Optional을 get으로 꺼냄
        Assertions.assertThat(member).isEqualTo(result); // 검증
    }
    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();
        Assertions.assertThat(result).isEqualTo(member1);
    }
    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        Assertions.assertThat(result.size()).isEqualTo(2);
    }
}
