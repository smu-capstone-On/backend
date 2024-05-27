package graduation.petshop.domain.auth.dto;

import lombok.Data;

@Data
public class UserDto {

    private String role;
    private String name;
    private String username;
}
