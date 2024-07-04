package graduation.petshop.domain.community.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "boardImage") //?
public class BoardImage {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String url;


//    @Column(nullable = false)
//    private String filename;
//    private String filepath;

//    private String originalFilename; //원본 파일명
//    private String saveFilename; //서버에 저장된 파일명

    @ManyToOne
    @JoinColumn(name = "BOARD_ID") //?
//    @JsonBackReference?
    private Board board;
}
