package graduation.petshop.domain.community.dto;

import graduation.petshop.domain.community.entity.Category;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BoardPatchDto {
    @NotEmpty
    private String title;
    @NotEmpty
    private String content;
    @NotEmpty
    private LocalDateTime createDate;
    @NotEmpty
    private LocalDateTime LastModifiedDate;
    @NotEmpty
    private Category category;
}