package graduation.petshop.domain.member.service;

import graduation.petshop.domain.member.dto.request.RequestCreateProfileDto;
import graduation.petshop.domain.member.entity.Member;
import graduation.petshop.domain.member.entity.Profile;
import graduation.petshop.domain.member.repository.MemberRepository;
import graduation.petshop.domain.member.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Long createProfile(Long memberId, RequestCreateProfileDto requestDto) {
        // 회원 조회
        Member member = memberRepository.findById(memberId);

        // 프로필 생성
        Profile profile = new Profile();
        profile.setNickName(requestDto.getNickName());
        profile.setSex(requestDto.getSex());
        profile.setAge(requestDto.getAge());

        // 프로필과 회원 연결
        profile.setMember(member);

        // 프로필 저장
        profileRepository.save(profile);

        // 생성된 프로필의 ID 반환
        return profile.getId();
    }
}
