package graduation.petshop.domain.member.repository;
import graduation.petshop.domain.member.entity.Profile;
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
}
