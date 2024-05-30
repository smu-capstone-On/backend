package graduation.petshop.domain.community.controller;

import graduation.petshop.domain.community.dto.ReplyPatchDto;
import graduation.petshop.domain.community.dto.ReplyPostDto;
import graduation.petshop.domain.community.dto.ReplyResponseDto;
import graduation.petshop.domain.community.service.ReplyService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Getter
@Setter
@RestController
@RequestMapping("/api/replies")
@RequiredArgsConstructor
public class ReplyController {
    private final ReplyService replyService;

//    public ResponseEntity postBoard(@RequestBody @Validated BoardPostDto boardPostDto) {
//        Long boardId = boardService.createBoard(boardPostDto);
//        return ResponseEntity.status(HttpStatus.CREATED).body(boardId);
//    }

    public ResponseEntity postReply(@PathVariable("boardId")Long boardId,
                                    @RequestBody @Validated ReplyPostDto replyPostDto, @AuthenticationPrincipal String email){
        Long replyId = replyService.writeReply(replyPostDto, boardId, email);
        return ResponseEntity.status(HttpStatus.CREATED).body(replyId);
    }


//
//    @PatchMapping("/{boardId}")
//    public ResponseEntity patchBoard(@PathVariable("boardId")Long boardId,
//                                     @RequestBody @Validated BoardPatchDto boardPatchDto, @AuthenticationPrincipal String email){
//        boardService.updateBoard(boardPatchDto, boardId, email);
//        return ResponseEntity.status(HttpStatus.OK).body(boardId);
//    }

    @PatchMapping
    public ResponseEntity updateReply(@PathVariable("replyId")Long replyId,
                                      @RequestBody @Validated ReplyPatchDto replyPatchDto, @AuthenticationPrincipal String email){
        replyService.updateReply(replyPatchDto, replyId, email);
        return ResponseEntity.status(HttpStatus.OK).body(replyId);
    }
//
//    @DeleteMapping("/{boardId}")
//    public ResponseEntity deleteBoard(@PathVariable("boardId") Long boardId,  @AuthenticationPrincipal String email) {
//        boardService.deleteBoard(boardId, email);
//        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//    }

    @DeleteMapping
    public ResponseEntity deleteReply(@PathVariable("replyId")Long replyId,@AuthenticationPrincipal String email ){
        replyService.deleteReply(replyId, email);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("{boardId}")
    public ResponseEntity<Page<ReplyResponseDto>> getAllReply(
            @PathVariable("boardId") Long boardId,
            @RequestParam(value = "page",defaultValue = "1")int page,
            @RequestParam(value = "size",defaultValue = "5")int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<ReplyResponseDto> replies = replyService.findAllReply(pageable,boardId);
        return ResponseEntity.status(HttpStatus.OK).body(replies);
    }
}
