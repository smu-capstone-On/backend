package graduation.petshop.domain.community.dto;

import graduation.petshop.domain.community.entity.Board;
import graduation.petshop.domain.profile.entity.Profile;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReplyPostDto {
    @NotEmpty
    private String recontent;
    @NotEmpty
    private Board board;
    @NotEmpty
    private Profile profile;
}
