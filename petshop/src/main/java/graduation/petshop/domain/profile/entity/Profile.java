package graduation.petshop.domain.profile.entity;
import graduation.petshop.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "profile")
@Getter
@SuperBuilder
public class Profile {

    @Id
    @GeneratedValue
    @Column(name = "profile_id")
    private Long id;

    @Column(nullable = false)
    private String nickName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender sex; // 성별 [FEMALE, MALE]

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PetStatus petStatus; // 산책반려동물 유무 [PETYES, PETNO]

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false)
    private String imagePath; // 이미지 파일 경로 (이미지는 디렉토리, 이미지 경로는 DB)

    // Member 엔티티와의 양방향 일대일 관계 설정
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;


    /* 닉네임 수정 로직*/
    public void modify(String nickName, PetStatus petStatus) {

        this.nickName = nickName;
        this.petStatus = petStatus;

    }

}