package graduation.petshop.domain.profile.service;


import graduation.petshop.domain.member.repository.MemberRepository;
import graduation.petshop.domain.member.service.MemberService;
import graduation.petshop.domain.profile.dto.request.JoinProfileDto;
import graduation.petshop.domain.profile.dto.request.ModifyProfileDto;
import graduation.petshop.domain.profile.entity.Profile;
import graduation.petshop.domain.profile.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final MemberRepository memberRepository;

    /*프로필 생성*/
    @Transactional
    public Long join(Long memberId,JoinProfileDto joinProfileDto) {
        validateDuplicateProfile(joinProfileDto.getNickName()); // 중복 닉네임 검증.
        Profile profile = Profile.createProfile(memberRepository.findById(memberId).get(), joinProfileDto);
        profileRepository.save(profile);
        return profile.getId();
    }

    /* 프로필 수정 더티체킹 - 닉네임과 팻 상태만 수정할 수 있도록. */
    @Transactional
    public void modifyProfile(Long profileId, ModifyProfileDto modifyProfileDto) {
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 프로필이 존재하지 않습니다."));
        profile.modify(modifyProfileDto.getNickName(), modifyProfileDto.getPetStatus());
    }

    protected void validateDuplicateProfile(String nickName) {
        List<Profile> findNickName = profileRepository.findByNickName(nickName);
        if (!findNickName.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 닉네임입니다.");
        }
    }

    /*프로필 조회 -> 나중에 마이페이지에서 찾을 수 있도록*/
    @Transactional(readOnly = true)
    public Profile findMyProfile(Long profileId) {
        return profileRepository.findById(profileId)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 프로필이 존재하지 않습니다."));
    }


}
