package graduation.petshop.domain.community.service;

import graduation.petshop.domain.community.dto.ReplyResponseDto;
import graduation.petshop.domain.community.entity.Board;
import graduation.petshop.domain.community.entity.Reply;
import graduation.petshop.domain.community.repository.BoardRepository;
import graduation.petshop.domain.community.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReplyService {

    private final BoardService boardService;
    private final ReplyRepository replyRepository;

    public Page<ReplyResponseDto> findAllReply(Pageable pageable, Long boardId) {
        Board board = boardService.findBoardId(boardId);
        Page<Reply> replies = replyRepository.findByBoard(board, pageable);
        return replies.map(ReplyResponseDto::FindFromReply);
    }
}
