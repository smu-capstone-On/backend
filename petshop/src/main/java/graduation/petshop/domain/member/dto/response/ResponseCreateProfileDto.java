package graduation.petshop.domain.member.dto.response;

import lombok.Data;

/**
 * 이건 지워도 상관 없을듯.
 */

@Data
public class ResponseCreateProfileDto {
    private Long profileId;
    private String message; // 저장 성공 여부 등의 메시지
}
