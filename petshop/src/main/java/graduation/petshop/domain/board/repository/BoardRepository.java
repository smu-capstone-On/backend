package graduation.petshop.domain.board.repository;

import graduation.petshop.domain.board.entity.Board;
import graduation.petshop.domain.board.entity.TagType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

    @Query("SELECT b FROM Board b JOIN FETCH b.boardTagList bt WHERE bt.tagType IN :boardTags")
    List<Board> getBoardFilter(List<TagType> boardTags);

    List<Board> findAllByTitleLike(String title);

    List<Board> findAllByMemberId(Long memberId);
}
