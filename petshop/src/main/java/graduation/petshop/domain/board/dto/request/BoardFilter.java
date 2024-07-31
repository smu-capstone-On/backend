package graduation.petshop.domain.board.dto.request;

import graduation.petshop.domain.board.entity.TagType;
import lombok.Getter;

import java.util.List;

@Getter
public class BoardFilter {
    private List<TagType> boardTags;
    private String title;
}
