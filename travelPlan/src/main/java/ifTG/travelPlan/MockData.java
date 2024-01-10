package ifTG.travelPlan;


import ifTG.travelPlan.domain.post.Post;
import ifTG.travelPlan.domain.user.Sex;
import ifTG.travelPlan.domain.user.User;
import ifTG.travelPlan.domain.user.UserAddress;
import ifTG.travelPlan.repository.springdata.post.PostRepository;
import ifTG.travelPlan.repository.springdata.user.UserAddressRepository;
import ifTG.travelPlan.repository.springdata.user.UserRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.Arrays;


@Transactional
@RequiredArgsConstructor
@Slf4j
public class MockData {
    private final UserAddressRepository userAddressRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    //@PostConstruct
    public void addMockData(){
        UserAddress userAddressA = UserAddress.builder()
                .sido("서울")
                .sigungu("강남")
                .eupmyendong("판교")
                .roadName("반포자이")
                .buildingNumber("34907")
                .zipCode("34907")
                .subBuildingNumber("63")
                .build();
        User userA = User.builder()
                .userId("dlaruddhks99")
                .pw("dla10241024@")
                .name("케인")
                .sex(Sex.FEMALE)
                .birthDate(LocalDate.of(1944, 12, 12))
                .phoneNumber("010-1234-5678")
                .email("kimdo@naver.com")
                .nickname("kaneTV")
                .userAddress(userAddressA)
                .build();

        UserAddress userAddressB = UserAddress.builder()
                .sido("대전")
                .sigungu("중구")
                .eupmyendong("오류동")
                .roadName("계룡로 852")
                .buildingNumber("34907")
                .zipCode("34907")
                .subBuildingNumber("31동 605호")
                .build();

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

        userAddressRepository.saveAll(Arrays.asList(userAddressA, userAddressB));
        userRepository.saveAll(Arrays.asList(userA, userB));

        /**
         * postA 정보
         * Like : 1개
         * Theme : 현장 체험, 모험
         * Region : 경기
         * Season : 가을
         * Companion : 혼자
         */
        Post postA = Post.builder()
                .title("2")
                .content("1")
                .user(userB)
                .startDate(LocalDate.of(2022, 10, 24))
                .endDate(LocalDate.of(2022, 10, 25))
                .build();
        log.info("postA persist");
        postRepository.save(postA);


        /**
         * Post B 정보
         * Like : 2개
         * Theme : 축제
         * Region : 대전
         * Companion : 친구들, 펫
         * season : 여름
         */
        Post postB = Post.builder()
                .title("ㅇㅇ아닌 유입")
                .content("ㅇㅇ")
                .user(userA)
                .startDate(LocalDate.of(2023, 6, 30))
                .endDate(LocalDate.of(2022, 7, 2)).build();
        postRepository.save(postB);


        /**
         * post C 정보
         * Like : 1개
         * companion : 가족
         * season : 겨울
         * 지역 : 충남
         */
        Post postC = Post.builder()
                .title("마라")
                .content("탕")
                .user(userB)
                .startDate(LocalDate.now())
                .endDate(LocalDate.now()).build();
        postRepository.save(postC);


        /**
         * postD 정보
         */
        Post postD = Post.builder()
                .title("꿔다놓은")
                .content("보릿자루")
                .user(userB)
                .startDate(LocalDate.now())
                .endDate(LocalDate.now()).build();
        postRepository.save(postD);
    }

}
