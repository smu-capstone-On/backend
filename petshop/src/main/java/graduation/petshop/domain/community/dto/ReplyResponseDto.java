package graduation.petshop.domain.community.dto;

import graduation.petshop.domain.community.entity.Board;
import graduation.petshop.domain.community.entity.Category;
import graduation.petshop.domain.community.entity.Reply;
import graduation.petshop.domain.member.entity.Member;
import graduation.petshop.domain.profile.entity.Profile;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Locale;

@Getter
@Setter
@AllArgsConstructor
public class ReplyResponseDto {

    private Long replyId;
    private String recontent;
    private Board board;
    private Profile profile;

    //정적 팩토리 메서드 추가
    public static ReplyResponseDto FindFromReply(Reply reply) {
        return new ReplyResponseDto(
                reply.getReplyId(),
                reply.getReContent(),
                reply.getBoard(),
                reply.getProfile()
        );
    }



}
