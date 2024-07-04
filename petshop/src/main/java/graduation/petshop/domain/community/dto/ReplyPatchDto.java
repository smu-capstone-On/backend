package graduation.petshop.domain.community.dto;

import graduation.petshop.domain.community.entity.Board;
import graduation.petshop.domain.community.entity.Category;
import graduation.petshop.domain.profile.entity.Profile;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReplyPatchDto {
    @NotEmpty
    private String recontent;
}