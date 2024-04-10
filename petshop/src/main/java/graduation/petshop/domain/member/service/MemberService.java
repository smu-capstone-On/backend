package graduation.petshop.domain.member.service;

import graduation.petshop.domain.member.entity.Member;
import graduation.petshop.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 회원가입
     * 중복조회 필요한가?
     */
    @Transactional
    public void join(Member member){
        memberRepository.save(member);
    }



    /**
     * 로그인 TODO 기능만들기
     */
    /*@Transactional
    public void login(Member member){

        memberRepository.save(member);
    }
     */

    /**
     * 이메일로 아이디 찾기
     * 일단 보류
     */
    public Member findIdByEmail(String email){
        return memberRepository.findByEmail(email);

    }


}
