package graduation.petshop.domain.community.dto;

import graduation.petshop.domain.community.entity.Board;
import graduation.petshop.domain.community.entity.Reply;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class ReplyResponseDto {
    private Long replyId;
    private String recontent;
    private Board board;

    //정적 팩토리 메서드 추가
    public static ReplyResponseDto FindFromReply(Reply reply) {
        return new ReplyResponseDto(
                reply.getReplyId(),
                reply.getReContent(),
                reply.getBoard()
        );
    }
}
