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

    //카테고리
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category;

    private String title; //제목

    private String content; //본문


    @Builder.Default
    @OneToMany(mappedBy = "board", cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    private List<Reply> reply = new ArrayList<>(); //댓글

//    private Integer commentCnt; //댓글 수

    //만들어진 시간
    @Builder.Default
    private LocalDateTime createDate = LocalDateTime.now();

    //변경된 시간
    @Builder.Default
    private LocalDateTime LastModifiedDate = createDate;

    //이미지
    @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
//    @OrderBy("id asc")
    private List<BoardImage> boardImages;

    //프로필
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id")
    private Profile profile;


    //프로필의 멤버
    @Builder.Default
    private Member member = profile.getMember();  // 합쳐야 합니다 여러분


    //좋아요 수
    @Builder.Default
    private Integer LikeCount = 0;

    //글 수정 비지니스 로직 추가.
//    public void UpdateBoard(Long boardId, String title, String content) {
//        this.boardId = boardId;
//        this.title = title;
//        this.content = content;
//    }
}

