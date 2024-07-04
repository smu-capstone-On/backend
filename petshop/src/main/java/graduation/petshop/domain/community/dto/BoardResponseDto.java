package graduation.petshop.domain.community.dto;

import graduation.petshop.domain.community.entity.Board;
import graduation.petshop.domain.community.entity.BoardImage;
import graduation.petshop.domain.community.entity.Category;
import graduation.petshop.domain.profile.entity.Profile;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BoardResponseDto {

    private Long boardId;
    private String title;
    private String content;
    private LocalDateTime LastModifiedDate;
    private Category category;
    private Profile profile;
    private List<String> imageUrls;

    //정적 팩토리 메서드 추가
    public static BoardResponseDto FindFromBoard(Board board) {
        return new BoardResponseDto(
                board.getBoardId(),
                board.getTitle(),
                board.getContent(),
                board.getLastModifiedDate(),
                board.getCategory(),
                board.getProfile(),
                board.getBoardImages().stream()
                        .map(BoardImage::getUrl)
                        .collect(Collectors.toList())
        );
    }
}