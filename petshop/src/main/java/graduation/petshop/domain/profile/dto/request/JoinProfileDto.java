package graduation.petshop.domain.profile.dto.request;

import graduation.petshop.domain.profile.entity.Gender;
import graduation.petshop.domain.profile.entity.PetStatus;
import graduation.petshop.domain.profile.entity.Profile;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotNull(message = "필수 입력 값입니다.")
    private Gender sex;

    @NotNull(message = "필수 입력 값입니다.")
    private Integer age;

    @NotNull(message = "필수 입력 값입니다.")
    private PetStatus petStatus;

    /* DTO -> Entity */

    // JoinProfileDto.java
    public Profile toEntity() {
        return Profile.builder()
                .nickName(this.nickName)
                .sex(this.sex)
                .age(this.age)
                .petStatus(this.petStatus)
                .build();
    }


}
