package graduation.petshop.domain.member.service;

import graduation.petshop.domain.member.dto.request.JoinDto;
import graduation.petshop.domain.member.entity.Member;
import graduation.petshop.domain.member.repository.MemberRepository;
import graduation.petshop.security.jwt.dto.CustomUserDetails;
import io.jsonwebtoken.security.Password;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    /**
     * 회원가입
     * 여기서 중복조회 하는게 더 낫나?
     */
    @Transactional
    public void join(JoinDto joinDto) {
        Member member = joinDto.toEntity(
                joinDto.getLoginId(),
                joinDto.getPassword(),
                joinDto.getEmail()
        );
        member.passwordEncode(passwordEncoder);
        memberRepository.save(member);
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

    /**
     * userdetailservice의
     * username은 DaoAuthenticationProvider가 토큰에서 꺼내서 설정해줌
     */
    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {

        Member member = memberRepository.findByLoginId(loginId)
                .orElseThrow(() -> new UsernameNotFoundException("해당 아이디가 존재하지 않습니다.")); ;

        return new CustomUserDetails(member);
    }
}
