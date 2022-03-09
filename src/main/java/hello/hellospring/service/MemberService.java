package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) { // alt + insert -> constructor생성
        this.memberRepository = memberRepository; //외부에서 memberRepository를 넣어줌 - DI 의존성주입
    }

    /**회원가입 */
    public Long join(Member member) {
        validateDuplicateMember(member);  //중복회원검증 메소드
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) { // 중복회원검증 메소드
        memberRepository.findByName(member.getName())
                .ifPresent(m->{ //ifPresent 값 존재하면 -> 값 들어옴(m) --> 로직 동작
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
              });
        /** 풀어쓰기
         Optional<Member> result = memberRepository.findByName(member.getName());
         result.ifPresent(m->{ //ifPresent 값 존재하면 -> 값 들어옴(m) --> 로직 동작
         throw new IllegalStateException("이미 존재하는 회원입니다.");
         }); */
    }
    /** 전체회원조회 */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }
    /** 아이디 조회 */
    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
