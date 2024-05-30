package graduation.petshop.domain.community.entity;

import graduation.petshop.domain.member.entity.Member;
import graduation.petshop.domain.profile.entity.Profile;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class Board {

    @Id
    @GeneratedValue
    @Column(name = "board_id")
    private Long boardId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category; /* DOG,CAT,FISH,BIRD,REPTILE [2020 인구주택총조사 만 19세 이상 남녀 700명을 대상으로 진행]*/

    private String title; //제목

    private String content; //본문

    @OneToMany(mappedBy = "board", cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    private List<Reply> reply = new ArrayList<>(); //댓글

//    private Integer commentCnt; //댓글 수

    private LocalDateTime createDate = LocalDateTime.now();

    private LocalDateTime LastModifiedDate = LocalDateTime.now();

//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "communityImage_id")
//    private CommunityImage communityImage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id")
    private Profile profile;






    
//    private Member member = profile.getMember();  이걸 해야 합니다 여러분
    /////////////////////근데 합쳐야해요






    private Integer LikeCount = 0;

    //글 수정 비지니스 로직 추가.
//    public void UpdateBoard(Long boardId, String title, String content) {
//        this.boardId = boardId;
//        this.title = title;
//        this.content = content;
//    }
}

