package graduation.petshop.domain.chat.controller;

import graduation.petshop.domain.chat.dto.request.ChatMessageRequestDto;
import graduation.petshop.domain.chat.dto.response.ChatMessageResponseDto;
import graduation.petshop.domain.chat.service.ChatMessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ChatController {

    private final ChatMessageService chatMessageService;
    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/send")
    public void sendMessage(@Payload ChatMessageRequestDto requestDto) {
        Long messageId = chatMessageService.save(requestDto);
        ChatMessageResponseDto responseDto = chatMessageService.findById(messageId);
        messagingTemplate.convertAndSendToUser(String.valueOf(requestDto.getRecipientId()), "/queue/messages", responseDto);

        log.info("Message sent successfully: {}", responseDto.toString());
    }

    @DeleteMapping("/chat/{messageId}")
    public void deleteMessage(@PathVariable Long messageId) {
        chatMessageService.delete(messageId);
    }
}
