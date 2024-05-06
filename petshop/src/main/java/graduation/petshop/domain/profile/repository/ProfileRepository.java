package graduation.petshop.domain.profile.repository;
import graduation.petshop.domain.profile.entity.Profile;
import graduation.petshop.exception.DuplicateNickNameException;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ProfileRepository {

    private final EntityManager em;

    //프로필 저장
    public Long save(Profile profile) {

        em.persist(profile);
        return profile.getId(); // 저장된 프로필의 ID 반환
    }

    // 프로필 id로 조회
    public Optional<Profile> findById(Long id) {
        Profile profile = em.find(Profile.class, id);
        return Optional.ofNullable(profile);
    }

    //닉네임 반환
    public List<Profile> findByNickName(String nickName) {
        return em.createQuery("select p from Profile p where p.nickName = :nickName", Profile.class)
                .setParameter("nickName", nickName)
                .getResultList();
    }
}