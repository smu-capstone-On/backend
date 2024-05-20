package graduation.petshop.domain.chat.dto.request;

import graduation.petshop.domain.chat.entity.ChatMessage;
import graduation.petshop.domain.profile.entity.Profile;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ChatMessageRequestDto {
    private String message;
    private Long senderId; // 보내는 사람의 프로필 ID
    private Long recipientId; // 받는 사람의 프로필 ID

    @Builder
    public ChatMessageRequestDto(String message, Long senderId, Long recipientId) {
        this.message = message;
        this.senderId = senderId;
        this.recipientId = recipientId;
    }

    public ChatMessage toEntity(Profile sender, Profile recipient) {
        return ChatMessage.builder()
                .message(this.message)
                .sender(sender)
                .recipient(recipient)
                .timestamp(LocalDateTime.now())
                .build();
    }
}
