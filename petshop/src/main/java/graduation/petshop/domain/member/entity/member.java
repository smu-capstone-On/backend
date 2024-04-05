package graduation.petshop.domain.member.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    private String nickName;

    private Integer age;

    private String sex;

    private String loginId;

    private String passWard;

    private String imageUrl;

    private LocalDateTime createDate;

    private LocalDateTime modifyDate;
}
