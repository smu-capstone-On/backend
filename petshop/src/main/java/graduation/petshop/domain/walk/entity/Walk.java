package graduation.petshop.domain.walk.entity;

import graduation.petshop.common.entity.Base;
import graduation.petshop.domain.member.entity.Member;
import graduation.petshop.domain.profile.entity.Profile;
import graduation.petshop.domain.walk.dto.request.JoinWalk;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
@AllArgsConstructor
public class Walk extends Base{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "walk_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id")
    private Profile profile;

    private LocalDateTime walkTime;

    private double speed;

    private double distance;

    //연관관계 메소드
    public void setProfile(Profile profile){
        this.profile = profile;
        profile.getWalk().add(this);
    }

    //산책 생성 메소드
    public static Walk createWalk(Profile profile, JoinWalk joinWalk)
    {
        Walk walk = new Walk();
        walk.setProfile(profile);
        walk.setWalkTime(joinWalk.getWalkTime());
        walk.setDistance(joinWalk.getDistance());
        walk.setSpeed(joinWalk.getSpeed());

        return walk;
    }
}
