package graduation.petshop.domain.community.entity;

import graduation.petshop.domain.member.entity.Member;
import graduation.petshop.domain.profile.entity.Profile;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class Reply {

    @Id
    @GeneratedValue
    @Column(name = "comment_id")
    private Long replyId;

    private String reContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @Builder.Default
    private Member member = profile.getMember();

    /* 비지니스 로직 추가 댓글 수정 기능 */
//    public void ModifyComment(Long id, String body) {
//        this.id = id;
//        this.body = body;
//    }
}
