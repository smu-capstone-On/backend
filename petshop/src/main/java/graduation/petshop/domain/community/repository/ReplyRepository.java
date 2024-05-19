package graduation.petshop.domain.community.repository;

import graduation.petshop.domain.community.entity.Board;
import graduation.petshop.domain.community.entity.Reply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Long> {

    Page<Reply> findByBoard(Board board, Pageable pageable);
}