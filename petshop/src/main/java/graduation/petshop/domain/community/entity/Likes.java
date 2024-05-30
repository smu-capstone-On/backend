package graduation.petshop.domain.community.entity;

import graduation.petshop.domain.member.entity.Member;
import graduation.petshop.domain.profile.entity.Profile;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.Id;
import org.springframework.security.core.parameters.P;

@Entity(name = "likes") //이거 이름 안바꿔 주면 충돌남 SQL 예약어라서..
@Setter @Getter
@NoArgsConstructor
public class Likes {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;
    // MEMBER_ID

    @ManyToOne
    @JoinColumn(name = "BOARD_ID")
    private Board board;


    //likeId는 생성자가 필요없어서 @AllArgsConstructor 사용 안했음.
    public Likes(Member member, Board board) {
        this.member = member;
        this.board = board;

    }
}