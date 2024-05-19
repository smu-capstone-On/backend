package graduation.petshop.domain.community.controller;

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
import org.springframework.web.bind.annotation.*;

@Getter
@Setter
@RestController
@RequestMapping("/api/replies")
@RequiredArgsConstructor
public class ReplyController {
    private final ReplyService replyService;

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
