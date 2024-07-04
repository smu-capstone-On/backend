package graduation.petshop.domain.community.repository;

import graduation.petshop.domain.community.entity.BoardImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardImageRepository extends JpaRepository<BoardImage, Long> {
}