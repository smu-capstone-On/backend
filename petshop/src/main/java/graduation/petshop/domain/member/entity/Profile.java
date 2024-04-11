package graduation.petshop.domain.member.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "profile")
@Getter
public class Profile {

    @Id @GeneratedValue
    @Column(name = "profile_id")
    private Long id;

    private String nickName;

    @Enumerated(EnumType.STRING)
    private Gender sex; // 성별 [FEMALE, MALE]

    private Integer age;

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setSex(Gender sex) {
        this.sex = sex;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @OneToOne(mappedBy = "profile", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Member member;
    public void setMember(Member member) {
        this.member = member;
    }
}

