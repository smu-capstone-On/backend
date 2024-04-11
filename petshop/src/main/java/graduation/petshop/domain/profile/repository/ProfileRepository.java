package graduation.petshop.domain.profile.repository;
import graduation.petshop.domain.profile.entity.Profile;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProfileRepository {

    private final EntityManager em;

    //프로필 저장
    public Profile save(Profile profile) {
        em.persist(profile);
        return profile;
    }

    // 프로필 조회
    public Profile findById(Long id) {
        return em.find(Profile.class, id);
    }
}
