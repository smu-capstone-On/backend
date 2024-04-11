package graduation.petshop.domain.member.dto.request;

import graduation.petshop.domain.member.entity.Gender;
import lombok.Data;

@Data
public class RequestCreateProfileDto {
    private String nickName;
    private Gender sex;
    private Integer age;
}
