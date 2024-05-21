package graduation.petshop.security.jwt.filter;

import graduation.petshop.domain.member.entity.Member;
import graduation.petshop.security.jwt.dto.CustomUserDetails;
import graduation.petshop.security.jwt.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Slf4j
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    public JwtFilter(JwtUtil jwtUtil){
        this.jwtUtil = jwtUtil;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //request에서 헤더찾음
        String authorization = request.getHeader("Authorization");

        //헤더 검증
        if(authorization == null || !authorization.startsWith("Bearer ")){
            log.info("토큰 없음");
            filterChain.doFilter(request, response);

            return;
        }

        //bearer 부분 제거 후 순수 토큰 획득
        String token = authorization.split(" ")[1];

        //토큰 소멸 시간 검증
        if (jwtUtil.isExpired(token)) {

            log.info("토큰 만료");
            filterChain.doFilter(request, response);

            //조건이 해당되면 메소드 종료 (필수)
            return;
        }

        //토큰에서 loginId 획득
        String loginId = jwtUtil.getLoginId(token);

        //멤버 객체 생성
        Member member = Member.builder()
                .loginId(loginId)
                .password("temppassword")
                .email("email")
                .build();

        //회원 정보 객체 담기
        CustomUserDetails customUserDetails = new CustomUserDetails(member);

        //security 인증 토큰 생성
        Authentication authToken = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());

        //사용자 등록 세선
        SecurityContextHolder.getContext().setAuthentication(authToken);

        filterChain.doFilter(request, response);
    }
}
