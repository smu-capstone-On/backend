package graduation.petshop.domain.board.controller;

import graduation.petshop.domain.board.dto.request.BoardRequest;
import graduation.petshop.domain.board.dto.request.BoardFilter;
import graduation.petshop.domain.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    @GetMapping
    public ResponseEntity<?> getAllBoard(Pageable pageable) {
        return ResponseEntity.ok(boardService.getAllBoard(pageable));
    }

    @GetMapping("/filter")
    public ResponseEntity<?> getBoardsFilter(@RequestBody BoardFilter boardFilter) {
        return ResponseEntity.ok(boardService.getBoardFilter(boardFilter));
    }

    @GetMapping("/{boardId}")
    public ResponseEntity<?> getBoard(@PathVariable Long boardId) {
        return ResponseEntity.ok(boardService.getBoard(boardId));
    }

    @PostMapping
    public ResponseEntity<?> saveBoard(@RequestBody BoardRequest boardRequest) {
        return ResponseEntity.ok(boardService.save(boardRequest));
    }
}
