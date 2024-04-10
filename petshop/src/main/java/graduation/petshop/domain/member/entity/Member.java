package graduation.petshop.domain.member.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    private String email;

    private String loginId;

    private String password;

    private String imageUrl;

    private LocalDateTime createDate;

    private LocalDateTime modifyDate;

    // Member 엔티티와 Profile 엔티티 간의 일대일 관계 설정
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_id")
    private Profile profile;

    // 비지니스 로직 추가. 프로필 수정
    public void updateProfile(Profile newProfile) {
        if (newProfile != null) {
            this.profile.setNickName(newProfile.getNickName());
            this.profile.setSex(newProfile.getSex());
            this.profile.setAge(newProfile.getAge());
        }
    }
}