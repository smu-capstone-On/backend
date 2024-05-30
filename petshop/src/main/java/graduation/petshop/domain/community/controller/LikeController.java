package graduation.petshop.domain.community.controller;

import graduation.petshop.domain.community.service.LikeService;
import graduation.petshop.domain.member.entity.Member;
import graduation.petshop.domain.member.service.MemberService;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

@RestController
@RequestMapping("/api/likes")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;
    private final MemberService memberService;

    @PostMapping("up/{boardId}")
    public ResponseEntity addLike(@PathVariable("boardId")@Positive Long boardId,
                                  @AuthenticationPrincipal String email ) {
        //이메일을 불러옴
        Member member = memberService.findIdByEmail(email);
        //id 랑 멤버 추가해 버림
        likeService.addLike(boardId,member);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}