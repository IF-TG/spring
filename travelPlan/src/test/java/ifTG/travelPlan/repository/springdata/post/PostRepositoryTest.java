package ifTG.travelPlan.repository.springdata.post;

import com.querydsl.jpa.impl.JPAQueryFactory;
import ifTG.travelPlan.domain.post.Post;
import ifTG.travelPlan.domain.user.Sex;
import ifTG.travelPlan.domain.user.User;
import ifTG.travelPlan.domain.user.UserAddress;
import ifTG.travelPlan.repository.springdata.user.UserAddressRepository;
import ifTG.travelPlan.repository.springdata.user.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
@Transactional
class PostRepositoryTest {
    @Autowired PostRepository postRepository;
    @PersistenceContext EntityManager em;
    JPAQueryFactory queryFactory;
    @Autowired UserAddressRepository userAddressRepository;
    @Autowired UserRepository userRepository;
    @BeforeEach
    public void before(){
        queryFactory = new JPAQueryFactory(em);
    }

    @Test
    public void findAll(){
        postRepository.findAll();
    }

    @Test
    public void save(){
        UserAddress userAddressB = UserAddress.builder()
                .sido("대전")
                .sigungu("중구")
                .eupmyendong("오류동")
                .roadName("계룡로 852")
                .buildingNumber("34907")
                .zipCode("34907")
                .subBuildingNumber("31동 605호")
                .build();
        userAddressRepository.save(userAddressB);

        User userB = User.builder()
                .userId("ruddhks99")
                .pw("r")
                .name("임경완")
                .sex(Sex.MALE)
                .birthDate(LocalDate.of(1999, 10, 24))
                .phoneNumber("010-6768-7937")
                .email("ruddhks00@gmail.com")
                .nickname("moondoooo")
                .userAddress(userAddressB)
                .build();
        userRepository.save(userB);

        Post postA = Post.builder()
                .title("2")
                .content("1")
                .user(userB)
                .startDate(LocalDate.of(2022, 10, 24))
                .endDate(LocalDate.of(2022, 10, 25))
                .build();

        postRepository.save(postA);
        postRepository.save(postA);

        System.out.println("PostRepositoryTest.save");
    }
}