package graduation.petshop.domain.profile.dto.request;

import graduation.petshop.domain.profile.entity.Gender;
import graduation.petshop.domain.profile.entity.PetStatus;
import graduation.petshop.domain.profile.entity.Profile;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JoinProfileDto {

    /** 회원 Service 요청(Request) DTO 클래스 */
    @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9-_]{4,10}$", message = "닉네임는 특수문자를 제외한 4~10자리여야 합니다.")
    @NotBlank(message = "필수 입력 값입니다.")
    private String nickName;

    @NotBlank(message = "필수 입력 값입니다.")
    private Gender sex;

    @NotBlank(message = "필수 입력 값입니다.")
    private Integer age;

    @NotBlank(message = "필수 입력 값입니다.")
    private PetStatus petStatus;

    /* DTO -> Entity */

    public Profile toEntity(String nickName, Gender sex, Integer age, PetStatus petStatus){
        return Profile.builder()
                .nickName(nickName)
                .sex(sex)
                .age(age)
                .petStatus(petStatus)
                .build();
    }

}
