package ifTG.travelPlan.repository.springdata.post;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Transactional
class PostRepositoryTest {
    @Autowired PostRepository postRepository;
    @PersistenceContext EntityManager em;
    JPAQueryFactory queryFactory;

    @BeforeEach
    public void before(){
        queryFactory = new JPAQueryFactory(em);
    }

    @Test
    public void findAll(){
        postRepository.findAll();
    }

    @Test
    public void 별읩려거테스트(){        queryFactory = new JPAQueryFactory(em);

    }
}