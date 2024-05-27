package graduation.petshop.domain.auth.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OAuthContorller {

    @RequestMapping("/kakao")
    public String kakaoOAuth(){
        return "안뇽";
    }
}
