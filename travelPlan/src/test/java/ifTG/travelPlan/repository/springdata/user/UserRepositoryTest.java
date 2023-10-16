package ifTG.travelPlan.repository.springdata.user;

import ifTG.travelPlan.domain.user.Sex;
import ifTG.travelPlan.domain.user.User;
import ifTG.travelPlan.domain.user.UserAddress;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
@Transactional
class UserRepositoryTest {
    @Autowired
    private EntityManager em;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserAddressRepository userAddressRepository;

    User userA;
    User userB;
    UserAddress userAddressA;
    UserAddress userAddressB;

    @BeforeEach
    void AddTestCase() {

        userAddressA = UserAddress.builder()
                .sido("서울")
                .sigungu("강남")
                .eupmyendong("판교")
                .roadName("반포자이")
                .buildingNumber("34907")
                .zipCode("34907")
                .subBuildingNumber("63")
                .build();
        userA = User.builder()
                .userId("dlaruddhks99")
                .pw("dla10241024@")
                .name("양승현")
                .sex(Sex.FEMALE)
                .birthDate(LocalDate.of(1944, 12, 12))
                .phoneNumber("010-1234-5678")
                .email("kimdo@naver.com")
                .userAddress(userAddressA)
                .build();

        userAddressB = UserAddress.builder()
                .sido("대전")
                .sigungu("중구")
                .eupmyendong("오류동")
                .roadName("계룡로 852")
                .buildingNumber("34907")
                .zipCode("34907")
                .subBuildingNumber("31동 605호")
                .build();

        userB = User.builder()
                .userId("ruddhks99")
                .pw("r")
                .name("임경완")
                .sex(Sex.MALE)
                .birthDate(LocalDate.of(1999, 10, 24))
                .phoneNumber("010-6768-7937")
                .email("ruddhks00@gmail.com")
                .userAddress(userAddressB)
                .build();

        userAddressRepository.saveAll(Arrays.asList(userAddressA, userAddressB));
        userRepository.saveAll(Arrays.asList(userA, userB));

    }

    @Test
    void findAll() {
        List<User> users = userRepository.findAll();
        assertThat(users).hasSize(2);
        assertThat(users.get(0).getPw()).isEqualTo(userA.getPw());
    }

    @Test
    void findAllWithPage() {
        Pageable pageable = PageRequest.of(0, 10);
        List<User> users = userRepository.findAll(pageable).toList();
        assertThat(users).hasSize(2);
        assertThat(users.get(0).getPw()).isEqualTo(userA.getPw());
    }

   @Test
    void findAllByName() {
        List<User> users = userRepository.findAllByName("임경완");
        assertThat(users).hasSize(1);
        assertThat(users.get(0).getUserId()).isEqualTo(userB.getUserId());
    }

    @Test
    void findAllBySex() {
        List<User> users = userRepository.findAllBySex(Sex.FEMALE);
        assertThat(users).hasSize(1);
        assertThat(users.get(0).getUserId()).isEqualTo(userA.getUserId());
    }

    @Test
    void findAllByBirthDate() {
        LocalDate localDate = LocalDate.of(1999,10,24);
        List<User> users = userRepository.findAllByBirthDate(localDate);
        assertThat(users).hasSize(1);
        assertThat(users.get(0).getUserId()).isEqualTo(userB.getUserId());
    }

    @Test
    void findAllWithCommentListById() {
        //추후
    }

    @Test
    void findAllWithPostListById() {
        //추후
    }

    @Test
    void findAllWithTravelPlansById() {
        //추후
    }

    @Test
    void findWithUserAddressById() {
        User user = userRepository.findByUserId("ruddhks99");
        User savedUser = userRepository.findWithUserAddressById(user.getId());
        assertThat(savedUser.getUserAddress().getSido()).isEqualTo("대전");
    }

    @Test
    void findByUserId() {
    }

    @Test
    void findByPhoneNumber() {
    }

    @Test
    void findByEmail() {
    }

    @Test
    void findByNickname() {
    }

}
