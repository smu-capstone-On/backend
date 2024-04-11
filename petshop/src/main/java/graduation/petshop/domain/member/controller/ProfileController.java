package graduation.petshop.domain.member.controller;

import graduation.petshop.domain.member.dto.request.RequestCreateProfileDto;
import graduation.petshop.domain.member.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/profiles")
public class ProfileController {

    private final ProfileService profileService;

    @PostMapping("/members/{memberId}")
    public ResponseEntity<Long> createProfile(@PathVariable Long memberId, @RequestBody @Validated RequestCreateProfileDto requestDto) {
        Long profileId = profileService.createProfile(memberId, requestDto);
        return ResponseEntity.ok(profileId);
    }
}
