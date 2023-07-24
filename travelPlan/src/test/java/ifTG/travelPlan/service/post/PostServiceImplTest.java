package ifTG.travelPlan.service.post;

import ifTG.travelPlan.domain.post.Post;
import ifTG.travelPlan.domain.user.*;
import ifTG.travelPlan.controller.dto.PostDto;
import ifTG.travelPlan.dto.post.PostRequestDto;
import ifTG.travelPlan.dto.post.enums.*;
import ifTG.travelPlan.repository.springdata.post.PostRepository;
import ifTG.travelPlan.repository.springdata.user.UserAddressRepository;
import ifTG.travelPlan.repository.springdata.user.UserBlockRepository;
import ifTG.travelPlan.repository.springdata.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@Transactional
@Slf4j
class PostServiceImplTest {
    private final PostService postService;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final UserAddressRepository userAddressRepository;
    private final UserBlockRepository userBlockRepository;

    @Autowired
    public PostServiceImplTest(PostService postService, UserAddressRepository userAddressRepository, PostRepository postRepository, UserRepository userRepository, UserBlockRepository userBlockRepository) {
        this.postService = postService;
        this.userAddressRepository = userAddressRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.userBlockRepository = userBlockRepository;
    }


    @BeforeEach
    void createInitData(){
        UserAddress userAddressA = UserAddress.builder()
                                              .sido("대전광역시")
                                              .sigungu("중구")
                                              .eupmyendong("오류동")
                                              .roadName("계룡로")
                                              .buildingNumber("852")
                                              .zipCode("34907")
                                              .subBuildingNumber("31동 605호")
                                              .build();

        User userA = User.builder()
                         .userId("ruddhks99")
                         .pw("rud10241024")
                         .name("임경완")
                         .sex(Sex.FEMALE)
                         .birthDate(LocalDate.now())
                         .phoneNumber("010-6768-7937")
                         .email("ruddhks99@naver.com")
                         .userAddress(userAddressA)
                         .build();


        UserAddress userAddressB = UserAddress.builder()
                                              .sido("경기도")
                                              .sigungu("은평구")
                                              .eupmyendong("케인동")
                                              .roadName("케인로")
                                              .buildingNumber("-3000")
                                              .zipCode("-1234")
                                              .subBuildingNumber("으헤헤헤")
                                              .build();

        User userB = User.builder()
                         .userId("kaneTV")
                         .pw("gayjoygo")
                         .name("케인")
                         .sex(Sex.MALE)
                         .birthDate(LocalDate.now())
                         .phoneNumber("010-1234-5678")
                         .email("ruddhks99@naver.com")
                         .userAddress(userAddressB)
                         .build();

        userAddressRepository.save(userAddressA);
        userAddressRepository.save(userAddressB);
        userRepository.save(userA);
        userRepository.save(userB);

        /**
         * userA는 userB를 차단
         */
        UserBlock userBlock = new UserBlock(new UserBlockId(userA.getId(), userB.getId()));
        userBlockRepository.save(userBlock);

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
                         .startDate(LocalDate.of(2022, 6, 30))
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


        postRepository.save(postA);
        postRepository.save(postB);
        postRepository.save(postC);
        postRepository.save(postD);
    }

    @Test
    void createPostDto() throws IllegalAccessException {
        PostRequestDto postRequestDtoA = new PostRequestDto(0, 4, OrderMethod.RECENT_ORDER, MainCategory.ORIGINAL, null, "ruddhks99");
        List<PostDto> lpA = postService.findAllPostWithPostRequestDto(postRequestDtoA);

        lpA.forEach(l->
                          log.info("{}, {}, {}, {}, {}", l.getPostId(), l.getTitle(), l.getContent(), l.getCreateAt(), l.getLikeNum())
                  );
        Assertions.assertThat(lpA.size()).isEqualTo(1);

        PostRequestDto postRequestDtoB = new PostRequestDto(0, 4, OrderMethod.RECENT_ORDER, MainCategory.ORIGINAL, null, "kaneTV");
        List<PostDto> lpB = postService.findAllPostWithPostRequestDto(postRequestDtoB);

        lpB.forEach(l->
                log.info("{}, {}, {}, {}, {}", l.getPostId(), l.getTitle(), l.getContent(), l.getCreateAt(), l.getLikeNum())
        );
        Assertions.assertThat(lpB.size()).isEqualTo(4);
    }
}