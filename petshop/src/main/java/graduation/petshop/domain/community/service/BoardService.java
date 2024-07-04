package graduation.petshop.domain.community.service;

import graduation.petshop.domain.community.dto.BoardImageUploadDto;
import graduation.petshop.domain.community.dto.BoardPatchDto;
import graduation.petshop.domain.community.dto.BoardPostDto;
import graduation.petshop.domain.community.dto.BoardResponseDto;
import graduation.petshop.domain.community.entity.Board;
import graduation.petshop.domain.community.entity.BoardImage;
import graduation.petshop.domain.community.entity.Reply;
import graduation.petshop.domain.community.repository.BoardImageRepository;
import graduation.petshop.domain.community.repository.BoardRepository;
import graduation.petshop.domain.community.repository.ReplyRepository;
//import graduation.petshop.domain.member.entity.Member;
import graduation.petshop.domain.member.entity.Member;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final BoardImageRepository boardImageRepository;

    @Value("${file.path}")
    private String uploadFolder;

    public Long createBoard(BoardPostDto boardPostDto, BoardImageUploadDto boardImageUploadDto) {
        Board result = Board.builder()
                .title(boardPostDto.getTitle())
                .content(boardPostDto.getContent())
                .createDate(boardPostDto.getCreateDate())
                .LastModifiedDate(boardPostDto.getLastModifiedDate())
                .category(boardPostDto.getCategory())
                .profile(boardPostDto.getProfile())
                .build();

        boardRepository.save(result);

        // 이 부분 추가
        if (boardImageUploadDto.getFiles() != null && !boardImageUploadDto.getFiles().isEmpty()) {
            for (MultipartFile file : boardImageUploadDto.getFiles()) {
                UUID uuid = UUID.randomUUID();
                String imageFileName = uuid + "_" + file.getOriginalFilename();

                File destinationFile = new File(uploadFolder + imageFileName);//uploadFolder + imageFileName);

                try {
                    file.transferTo(destinationFile);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                BoardImage image = BoardImage.builder()
                        .url("/boardImages/" + imageFileName)
                        .board(result)
                        .build();

                boardImageRepository.save(image);
            }
        }

        return result.getBoardId();

//        Board board = new Board();
//        board.setTitle(boardPostDto.getTitle());
//        board.setContent(boardPostDto.getContent());
//        board.setCreateDate(boardPostDto.getCreateDate());
//        board.setLastModifiedDate(boardPostDto.getLastModifiedDate());
//        board.setCategory(boardPostDto.getCategory());
//        board.setProfile(boardPostDto.getProfile());
//        //        board.setBoardImages(boardPatchDto.getImageUrls());
//
//        if (boardImageUploadDto.getFiles() != null && !boardImageUploadDto.getFiles().isEmpty()) {
//            for (MultipartFile file : boardImageUploadDto.getFiles()) {
//                UUID uuid = UUID.randomUUID();
//                String imageFileName = uuid + "_" + file.getOriginalFilename();
//
//                File destinationFile = new File(uploadFolder + imageFileName);
//
//                try {
//                    file.transferTo(destinationFile);
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//
//                BoardImage image = BoardImage.builder()
//                        .url("/boardImages/" + imageFileName)
//                        .board(result)
//                        .build();
//
//                boardImageRepository.save(image);
//            }
//        }
//
//        return boardRepository.save(board).getBoardId();
    }

    public Long updateBoard(BoardPatchDto boardPatchDto, Long boardId,String email, @ModelAttribute BoardImageUploadDto boardImageUploadDto) {
        Board board = findBoardId(boardId);
        isPermission(board.getMember(),email);

        board.setTitle(boardPatchDto.getTitle());
        board.setContent(boardPatchDto.getContent());
        board.setLastModifiedDate(LocalDateTime.now());
        board.setCategory(boardPatchDto.getCategory());
        board.setProfile(boardPatchDto.getProfile());
//        board.setBoardImages(boardPatchDto.getImageUrls());

        return boardRepository.save(board).getBoardId();
    }

    public void deleteBoard(Long boardId,String email) {
        Board board = findBoardId(boardId);
        isPermission(board.getMember(),email);

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