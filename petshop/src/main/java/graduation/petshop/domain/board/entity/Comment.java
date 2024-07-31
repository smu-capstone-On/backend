package graduation.petshop.domain.board.entity;

import graduation.petshop.common.entity.Base;
import graduation.petshop.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
public class Comment extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "comment_id")
    private Long id;

    private String body;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private void setBoard(Board board) {
        this.board = board;
        board.getComments().add(this);
    }

    private void setMember(Member member) {
        this.member = member;
    }

    public Comment(Board board, Member member) {
        this.setMember(member);
        this.setBoard(board);
    }
}
