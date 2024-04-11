package graduation.petshop.domain.profile.dto.response;

import graduation.petshop.domain.profile.entity.Gender;
import graduation.petshop.domain.profile.entity.PetStatus;
import lombok.Data;

@Data
public class ResponseFindProfileDto {
    private Long profileId;
    private String nickName;
    private Gender sex;
    private Integer age;
    private PetStatus petStatus;
}
