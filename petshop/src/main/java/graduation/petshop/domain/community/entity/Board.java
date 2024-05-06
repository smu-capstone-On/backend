package graduation.petshop.domain.community.entity;

import graduation.petshop.domain.profile.entity.Profile;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor
public class Board {

    @Id
    @GeneratedValue
    @Column(name = "board_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category; /* DOG,CAT,FISH,BIRD,REPTILE [2020 인구주택총조사 만 19세 이상 남녀 700명을 대상으로 진행]*/

    private String title; //제목

    private String body; //본문

    @OneToMany(mappedBy = "board", orphanRemoval = true)
    private List<Comment> comments; //댓글

    private Integer commentCnt; //댓글 수

    private LocalDateTime createDate;

    private LocalDateTime LastModifiedDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "communityImage_id")
    private CommunityImage communityImage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id")
    private Profile profile;


    //글 수정 비지니스 로직 추가.
    public void ModifyBoard(Long id, String title, String body) {
        this.id = id;
        this.title = title;
        this.body = body;
    }
}

