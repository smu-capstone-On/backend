package graduation.petshop.domain.member.service;

import graduation.petshop.domain.member.entity.Member;
import graduation.petshop.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 회원가입
     */
    @Transactional
    public void join(Member member) {
        memberRepository.save(member);
    }


    /**
     * 아이디와 패스워드가 일치하지 않으면 null 반환
     */
    public Member login(String loginId, String password) {
        return memberRepository.findByLoginId(loginId)
                .filter(m -> m.getPassword().equals(password))
                .orElse(null);
        /*if(member.get().getPassword().equals(password)){
            return member.get();
        }
        return null;

         */
    }


    /**
     * 이메일로 아이디 찾기
     *
     */
        public Member findIdByEmail (String email){
            return memberRepository.findByEmail(email).get();
        }

        /**
         * 로그인아이디로 비밀번호 찾기
         */
        public Member findPwdByLoginId (String loginId){
            return memberRepository.findByLoginId(loginId).get();
        }

        /**
         * 회원가입 아이디 중복 확인
         */
        public void checkLoginId (String loginId){
            Optional<Member> member = memberRepository.findByLoginId(loginId);
            if(member.isPresent()){
                throw new IllegalStateException();
            }
        }

    }
