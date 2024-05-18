package graduation.petshop.domain.community.service;

import graduation.petshop.domain.community.dto.BoardPostDto;
import graduation.petshop.domain.community.entity.Board;
import graduation.petshop.domain.community.repository.BoardRepository;
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

}