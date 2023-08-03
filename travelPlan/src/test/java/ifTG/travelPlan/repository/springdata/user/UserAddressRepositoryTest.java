package ifTG.travelPlan.repository.springdata.user;

import ifTG.travelPlan.domain.user.Sex;
import ifTG.travelPlan.domain.user.User;
import ifTG.travelPlan.domain.user.UserAddress;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserAddressRepositoryTest {
    @Autowired
    UserAddressRepository userAddressRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    EntityManager em;

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
    void findAllBySido() {
        List<UserAddress> userAddress = userAddressRepository.findAllBySido("대전");
        assertThat(userAddress).hasSize(1);
        assertThat(userAddress.get(0).getSido()).isEqualTo("대전");
    }

    @Test
    void findWithUserById() {
        User user = userRepository.findByUserId("ruddhks99");
        UserAddress userAddress = userAddressRepository.findWithUserById(user.getId());
        assertThat(userAddress.getUser().getName()).isEqualTo("임경완");
        assertThat(userAddress.getSido()).isEqualTo("대전");
    }

    @Test
    void countGroupBySido() {
        List<Object[]> count = userAddressRepository.countGroupBySido();
        assertThat(count.get(0)[0]).isEqualTo("서울");
        assertThat(count.get(0)[1]).isEqualTo(1L);
        assertThat(count.get(1)[0]).isEqualTo("대전");
        assertThat(count.get(1)[1]).isEqualTo(1L);
    }
}