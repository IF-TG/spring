package ifTG.travelPlan;

import ifTG.travelPlan.domain.post.Post;
import ifTG.travelPlan.domain.user.Sex;
import ifTG.travelPlan.domain.user.User;
import ifTG.travelPlan.domain.user.UserAddress;
import ifTG.travelPlan.repository.springdata.NestedCommentRepository;
import ifTG.travelPlan.repository.springdata.post.CommentRepository;
import ifTG.travelPlan.repository.springdata.post.PostRepository;
import ifTG.travelPlan.repository.springdata.user.UserAddressRepository;
import ifTG.travelPlan.repository.springdata.user.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
@Slf4j
public class InitData {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final NestedCommentRepository nestedCommentRepository;
    private final UserRepository userRepository;
    private final UserAddressRepository userAddressRepository;

    @PostConstruct
    public void initData(){
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
                .name("양승현")
                .sex(Sex.FEMALE)
                .birthDate(LocalDate.of(1944, 12, 12))
                .phoneNumber("010-1234-5678")
                .email("kimdo@naver.com")
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
                .title("나는! 장품을! 했다!")
                .content("이 케인님을 뭘로 보고!")
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
                .title("제발 좀 되라")
                .content("테스트 코드 짜는게 더 힘들어 ㅁㅊ")
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
                .title("차라투스트라는 이렇게 말했다.")
                .content("낙타 개시끄럽네")
                .user(userB)
                .startDate(LocalDate.now())
                .endDate(LocalDate.now()).build();
        postRepository.save(postC);



        /**
         * postD 정보
         */
        Post postD = Post.builder()
                .title("게이는 문화다. 게이는 문화다. 게이는 문화다")
                .content("게이조이고")
                .user(userB)
                .startDate(LocalDate.now())
                .endDate(LocalDate.now()).build();
        postRepository.save(postD);



    }
}
