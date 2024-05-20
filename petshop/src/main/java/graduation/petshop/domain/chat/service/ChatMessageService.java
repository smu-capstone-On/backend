package graduation.petshop.domain.chat.service;

import graduation.petshop.domain.chat.dto.request.ChatMessageRequestDto;
import graduation.petshop.domain.chat.dto.response.ChatMessageResponseDto;
import graduation.petshop.domain.chat.entity.ChatMessage;
import graduation.petshop.domain.chat.repository.ChatRepository;
import graduation.petshop.domain.profile.entity.Profile;
import graduation.petshop.domain.profile.repository.ProfileRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ChatMessageService {

    private final ChatRepository chatRepository;
    private final ProfileRepository profileRepository;

    /** ChatMessage 조회 */
    @Transactional(readOnly = true)
    public ChatMessageResponseDto findById(final Long chatMessageId) {
        ChatMessage chatMessageEntity = this.chatRepository.findById(chatMessageId)
                .orElseThrow(() -> new IllegalArgumentException("해당 ChatMessage가 존재하지 않습니다. chatMessageId = " + chatMessageId));
        return new ChatMessageResponseDto(chatMessageEntity);
    }

    /** ChatMessage 생성 */
    @Transactional
    public Long save(final ChatMessageRequestDto requestDto) {
        // 보낸 사람과 받는 사람의 프로필 정보 가져오기
        Profile sender = profileRepository.findById(requestDto.getSenderId())
                .orElseThrow(() -> new IllegalArgumentException("보내는 사람의 프로필이 존재하지 않습니다. profileId = " + requestDto.getSenderId()));
        Profile recipient = profileRepository.findById(requestDto.getRecipientId())
                .orElseThrow(() -> new IllegalArgumentException("받는 사람의 프로필이 존재하지 않습니다. profileId = " + requestDto.getRecipientId()));

        // ChatMessage 엔티티 생성
        ChatMessage chatMessageEntity = requestDto.toEntity(sender, recipient);
        chatRepository.save(chatMessageEntity);
        return chatMessageEntity.getId();
    }

    /** ChatMessage 삭제 */
    @Transactional
    public void delete(final Long chatMessageId) {
        ChatMessage chatMessageEntity = this.chatRepository.findById(chatMessageId)
                .orElseThrow(() -> new IllegalArgumentException("해당 ChatMessage가 존재하지 않습니다. chatMessageId = " + chatMessageId));
        this.chatRepository.delete(chatMessageEntity);
    }
}