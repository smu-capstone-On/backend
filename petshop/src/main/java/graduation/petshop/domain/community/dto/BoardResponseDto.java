package graduation.petshop.domain.community.dto;

import graduation.petshop.domain.community.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BoardResponseDto {

    private Long boardId;
    private String title;
    private String content;

    //정적 팩토리 메서드 추가
    public static BoardResponseDto FindFromBoard(Board board) {
        return new BoardResponseDto(
                board.getBoardId(),
                board.getTitle(),
                board.getContent()
        );
    }
}