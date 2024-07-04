package graduation.petshop.domain.community.entity;

import graduation.petshop.domain.member.entity.Member;
import graduation.petshop.domain.profile.entity.Profile;
import jakarta.persistence.*;
import lombok.Builder;
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


    @Builder.Default
    @OneToMany(mappedBy = "board", cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    private List<Reply> reply = new ArrayList<>(); //댓글

//    private Integer commentCnt; //댓글 수

    @Builder.Default
    private LocalDateTime createDate = LocalDateTime.now();

    @Builder.Default
    private LocalDateTime LastModifiedDate = createDate;

//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "communityImage_id")
//    private CommunityImage communityImage;

//    @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
//    private List<Image> communityImages;

    // cascade = CascadeType.ALL : 부모 엔티티(board)에서 생성, 업데이트, 삭제되면 image도 동일하게 처리
    // orphanRemoval = true : 부모 엔티티(board)에서 image를 참조 제거하면 image엔티티에서도 DB에서 삭제
//    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<BoardImage> images = new ArrayList<>(); // 연관된 이미지들

    @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
//    @OrderBy("id asc")
    private List<BoardImage> boardImages;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id")
    private Profile profile;


    @Builder.Default
    private Member member = profile.getMember();  //이걸 해야 합니다 여러분
    /////////////////////근데 합쳐야해요






    @Builder.Default
    private Integer LikeCount = 0;

    //글 수정 비지니스 로직 추가.
//    public void UpdateBoard(Long boardId, String title, String content) {
//        this.boardId = boardId;
//        this.title = title;
//        this.content = content;
//    }
}

