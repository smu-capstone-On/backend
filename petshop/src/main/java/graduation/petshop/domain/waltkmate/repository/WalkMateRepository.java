package graduation.petshop.domain.waltkmate.repository;

import graduation.petshop.domain.waltkmate.entity.SexType;
import graduation.petshop.domain.waltkmate.entity.WalkMate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WalkMateRepository extends JpaRepository<WalkMate, Long> {

    List<WalkMate> findAllBySexTypeAndHasPetAndAgeBetween(SexType sexType, Boolean hasPet, Integer minAge, Integer maxAge);
}
