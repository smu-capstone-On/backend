package graduation.petshop.domain.chat.repository;

import graduation.petshop.domain.chat.entity.ChatMessage;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChatRepository extends JpaRepository<ChatMessage, Long> {

    /** ChatMessage 검색조회 - 기본정렬순, Message 검색 */
    Optional<ChatMessage> findByMessageContaining(String message);

    /** ChatMessage 검색조회 - 조건정렬순, Message 검색 */
    Optional<ChatMessage> findByMessageContaining(String message, Sort sort);
}