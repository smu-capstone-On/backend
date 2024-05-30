package graduation.petshop.domain.community.service;

import graduation.petshop.domain.community.dto.ReplyPatchDto;
import graduation.petshop.domain.community.dto.ReplyPostDto;
import graduation.petshop.domain.community.dto.ReplyResponseDto;
import graduation.petshop.domain.community.entity.Board;
import graduation.petshop.domain.community.entity.Reply;
import graduation.petshop.domain.community.repository.BoardRepository;
import graduation.petshop.domain.community.repository.ReplyRepository;
import graduation.petshop.domain.member.entity.Member;
import graduation.petshop.domain.member.repository.MemberRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReplyService {

    private final BoardService boardService;

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final ReplyRepository replyRepository;

    public Long writeReply(ReplyPostDto replyPostDto, Long boardId, String email) {
        Member member = memberRepository.findByEmail(email);
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new IllegalArgumentException("게시물을 찾을 수 없습니다."));

        Reply reply = new Reply();
        reply.setReContent(replyPostDto.getRecontent());
        reply.setProfile(replyPostDto.getProfile());
        reply.setBoard(replyPostDto.getBoard());

        return replyRepository.save(reply).getReplyId();
    }

    public Long updateReply(ReplyPatchDto replyPatchDto, Long replyId, String email) {

        Reply reply = replyRepository.findById(replyId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 댓글입니다."));
        isPermission(reply.getMember(),email);

        reply.setReContent(replyPatchDto.getRecontent());

        return replyRepository.save(reply).getReplyId();
    }

    public void deleteReply(Long replyId, String email) {

        Reply reply = replyRepository.findById(replyId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 댓글입니다."));
        isPermission(reply.getMember(),email);

        replyRepository.deleteById(replyId);
    }


    public Page<ReplyResponseDto> findAllReply(Pageable pageable, Long boardId) {
        Board board = boardService.findBoardId(boardId);
        Page<Reply> replies = replyRepository.findByBoard(board, pageable);
        return replies.map(ReplyResponseDto::FindFromReply);
    }









    public class BusinessLogicException extends RuntimeException{

        @Getter
        private BoardService.ExceptionCode exceptionCode;

        public BusinessLogicException(BoardService.ExceptionCode exceptionCode) {
            super(exceptionCode.getMessage());
            this.exceptionCode = exceptionCode;
        }
    }

    public enum ExceptionCode {
        BOARD_NOT_FOUND(400, "board not found"),
        NO_PERMISSION(403, "don't have permission");

        @Getter
        private int status;
        @Getter
        private String message;

        ExceptionCode(int status, String message) {
            this.status = status;
            this.message = message;
        }
    }

    public void isPermission(Member member, String email) {
        if (!member.getEmail().equals(email)) {
            throw new BusinessLogicException(BoardService.ExceptionCode.NO_PERMISSION);
        }
    }
}
