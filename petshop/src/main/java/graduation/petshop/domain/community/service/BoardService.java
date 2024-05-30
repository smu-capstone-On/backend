package graduation.petshop.domain.community.service;

import graduation.petshop.domain.community.dto.BoardPatchDto;
import graduation.petshop.domain.community.dto.BoardPostDto;
import graduation.petshop.domain.community.dto.BoardResponseDto;
import graduation.petshop.domain.community.entity.Board;
import graduation.petshop.domain.community.entity.Reply;
import graduation.petshop.domain.community.repository.BoardRepository;
import graduation.petshop.domain.community.repository.ReplyRepository;
import graduation.petshop.domain.member.entity.Member;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;


    public Long createBoard(BoardPostDto boardPostDto) {
        Board board = new Board();
        board.setTitle(boardPostDto.getTitle());
        board.setContent(boardPostDto.getContent());
        board.setCreateDate(boardPostDto.getCreateDate());
        board.setCategory(boardPostDto.getCategory());
//        board.setMember(memberService.displayNickname(boardPostDto.getMember())); 이걸 프로필로 해야하는건가

        return boardRepository.save(board).getBoardId();
    }

    public Long updateBoard(BoardPatchDto boardPatchDto, Long boardId) { //,String email) {
        Board board = findBoardId(boardId);
//        isPermission(board.getMember(),email);
        board.setTitle(boardPatchDto.getTitle());
        board.setContent(boardPatchDto.getContent());
        board.setLastModifiedDate(boardPatchDto.getLastModifiedDate());
        board.setCategory(boardPatchDto.getCategory());

        return boardRepository.save(board).getBoardId();

    }

    public void deleteBoard(Long boardId) { ////,String email) {
        //        isPermission(board.getMember(),email);
        findBoardId(boardId);

        boardRepository.deleteById(boardId);
    }






    //

    public Board findBoardId(Long boardId) {
        return boardRepository.findById(boardId)
                .orElseThrow(()->new BusinessLogicException(ExceptionCode.BOARD_NOT_FOUND));
    }






    public class BusinessLogicException extends RuntimeException{

        @Getter
        private ExceptionCode exceptionCode;

        public BusinessLogicException(ExceptionCode exceptionCode) {
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
            throw new BusinessLogicException(ExceptionCode.NO_PERMISSION);
        }
    }




    // 정적인데 변환 필요





    public BoardResponseDto findByBoardId(Long boardId) {

        Board board = findBoardId(boardId);
        return BoardResponseDto.FindFromBoard(board);
    }


    public Page<BoardResponseDto> findAllBoards(Pageable pageable) {
        Page<Board> boards = boardRepository.findAll(pageable);
        return boards.map(BoardResponseDto::FindFromBoard); //board -> BoardResponseDto.FindFromBoard(board)
    }


}