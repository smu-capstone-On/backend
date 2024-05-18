package graduation.petshop.domain.community.service;

import graduation.petshop.domain.community.dto.BoardPatchDto;
import graduation.petshop.domain.community.dto.BoardPostDto;
import graduation.petshop.domain.community.entity.Board;
import graduation.petshop.domain.community.repository.BoardRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public Long createBoard(BoardPostDto boardPostDto) {
        Board board = new Board();
        board.setTitle(boardPostDto.getTitle());
        board.setContent(board.getContent());

        return boardRepository.save(board).getBoardId();
    }

    public Long updateBoard(BoardPatchDto boardPatchDto, Long boardId) {
        Board board = findBoardId(boardId);
        board.setTitle(boardPatchDto.getTitle());
        board.setContent(boardPatchDto.getContent());

        return boardRepository.save(board).getBoardId();

    }

    public void deleteBoard(Long boardId) {
        findBoardId(boardId);
        boardRepository.deleteById(boardId);
    }





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


        BOARD_NOT_FOUND(400, "board not found");

        @Getter
        private int status;
        @Getter
        private String message;

        ExceptionCode(int status, String message) {
            this.status = status;
            this.message = message;
        }
    }

}