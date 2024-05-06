package graduation.petshop.domain.profile.entity;
import graduation.petshop.domain.community.entity.Board;
import graduation.petshop.domain.community.entity.Comment;
import graduation.petshop.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor
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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profileImage_id")
    private ProfileImage profileImage;

    // Member 엔티티와의 양방향 일대일 관계 설정
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "profile",orphanRemoval=true)
    private List<Board> board;

    @OneToMany(mappedBy = "profile",orphanRemoval = true)
    private List<Comment> comment;

    /* 닉네임 수정 로직*/
    public void modify(String nickName, PetStatus petStatus) {

        this.nickName = nickName;
        this.petStatus = petStatus;

    }

}