package graduation.petshop.domain.community.service;

import graduation.petshop.domain.community.entity.Board;
import graduation.petshop.domain.community.entity.Likes;
import graduation.petshop.domain.community.repository.BoardRepository;
import graduation.petshop.domain.community.repository.LikeRepository;
import graduation.petshop.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class LikeService {

    private final BoardService boardService;
    private final BoardRepository boardRepository;
    private final LikeRepository likeRepository;

    public void addLike(Long boardId, Member member) {

        Board board = boardService.findBoardId(boardId);
        if (!likeRepository.existsByMemberAndBoard(member, board)) {
            // 호출되면 board에 있는 count 증가
            board.setLikeCount(board.getLikeCount()+1);
            // likeRepository에 memberId 값이랑 boardId값 저장해버림
            likeRepository.save(new Likes(member, board));
        }
        else {
            board.setLikeCount(board.getLikeCount()-1);
            likeRepository.deleteByMemberAndBoard(member,board);
        }
//        boardRepository.save(board); //@Transactional 사용하니깐 더티 체킹으로 필요없어짐
    }
}