package graduation.petshop.domain.community.entity;

import graduation.petshop.domain.profile.entity.Profile;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor
public class CommunityImage {

    @Id
    @GeneratedValue
    private Long id;

    private String originalFilename; //원본 파일명
    private String saveFilename; //서버에 저장된 파일명

    @OneToOne(mappedBy = "communityImage",fetch = FetchType.LAZY)
    private Board board;


}
