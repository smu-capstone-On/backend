package graduation.petshop.domain.profile.dto.request;

import graduation.petshop.domain.profile.entity.Gender;
import graduation.petshop.domain.profile.entity.PetStatus;
import lombok.Data;

@Data
public class RequestCreateProfileDto {
    private String nickName;
    private Gender sex;
    private Integer age;
    private PetStatus petStatus;
}
