package graduation.petshop.domain.chat.repository;

import graduation.petshop.domain.chat.entity.ChatMessage;
import graduation.petshop.domain.chat.entity.ChatRoom;
import graduation.petshop.domain.profile.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    Optional<ChatRoom> findByUser1AndUser2(Profile user1, Profile user2);

    List<ChatRoom> findByUser1OrUser2(Profile user1, Profile user2);


}



