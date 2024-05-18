package graduation.petshop.domain.community.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardPatchDto {
    @NotEmpty
    private String title;
    @NotEmpty
    private String content;
}