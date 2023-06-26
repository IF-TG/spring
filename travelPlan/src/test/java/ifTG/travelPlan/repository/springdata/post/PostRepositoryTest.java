package ifTG.travelPlan.repository.springdata.post;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostRepositoryTest {
    @Autowired PostRepository postRepository;

    @Test
    public void findAll(){
        postRepository.findAll();
    }
}