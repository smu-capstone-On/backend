package graduation.petshop.domain.auth.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import graduation.petshop.domain.auth.dto.response.LoginPageUriDto;
import graduation.petshop.domain.auth.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthService authService;

    /**
     * 로그인 페이지 url 넘겨주기
     *
     */
    @GetMapping("/kakao/page")
    public LoginPageUriDto kakaoLoginPage(){
        String page = authService.KakaoLoginPage();
        return new LoginPageUriDto(page);
    }

    /**
     *
     * 인가코드 받고
     * 토큰발급 하고
     * 사용자 정보에 저장하기
     */
    @RequestMapping("/kakao/login")
    public ResponseEntity<Object> kakaoLogin(@RequestParam("code") String code, HttpServletRequest request) throws Throwable {
        log.info("카카오 로그인");
        log.info("code : " +code);

        String kakaoAccessToken = authService.getKakaoAccessToken(code);
        log.info("kakaoAcessToken:" + kakaoAccessToken);

        String nickname = authService.getUserInfo(kakaoAccessToken);
        return ResponseEntity.ok("ok");
    }

}
