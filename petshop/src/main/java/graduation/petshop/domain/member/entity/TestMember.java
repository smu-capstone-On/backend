package graduation.petshop.domain.member.entity;


import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@AllArgsConstructor
@Transactional
public class TestMember {

    private static EntityManager em;

    public static void main(String[] args) {
        member member = new member();
        member.setAge(3);
        em.persist(member);
    }
}
