package graduation.petshop.domain.community.repository;

import graduation.petshop.domain.community.entity.Board;
import graduation.petshop.domain.community.entity.Likes;
import graduation.petshop.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Likes,Long> {

    //있는지 없는지 검토
    boolean existsByMemberAndBoard(Member member, Board board);
    //삭제
    void deleteByMemberAndBoard(Member member, Board board);

}