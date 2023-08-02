package ifTG.travelPlan.repository.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import ifTG.travelPlan.domain.post.Post;
import ifTG.travelPlan.domain.user.Sex;
import ifTG.travelPlan.domain.user.User;
import ifTG.travelPlan.domain.user.UserAddress;
import ifTG.travelPlan.repository.springdata.post.PostRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@Transactional
@SpringBootTest
@Slf4j
class QPostListRepositoryTest {
    @PersistenceContext
    EntityManager em;
    JPAQueryFactory queryFactory;
    @Autowired
    QPostListRepository qPostListRepository;
    @Autowired
    PostRepository postRepository;

    @BeforeEach
    void before(){
        queryFactory = new JPAQueryFactory(em);

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
        em.persist(userAddressA);
        em.persist(userAddressB);
        em.persist(userA);
        em.persist(userB);

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



        postRepository.save(postA);
        postRepository.save(postB);
        postRepository.save(postC);
        postRepository.save(postD);

    }

    @Test
    void test(){

    }
   /* @Test
    void findAllWithCompanionBySubCategory() {
        em.flush();
        em.clear();

        Pageable pageable = PageRequest.of(0, 4);
        OrderMethod orderMethod = OrderMethod.RECOMMEND_ORDER;
        Companions subCategory = Companions.ALONE;

        Page<Post> findPostList = qPostRepository.findAllWithCompanionBySubCategory(pageable, orderMethod, subCategory, null);
        for(Post p : findPostList){
            log.info("{}, {}, {}, {}, {}, {}, {}, {}",
                    p.getUser().getId(),
                    p.getTitle(),
                    p.getUser().getUserId(),
                    p.getStartDate(),
                    p.getEndDate(),
                    p.getContent(),
                    p.getLikeNum(),
                    p.getCommentNum());
        }

        Assertions.assertThat(findPostList.getTotalElements()).isEqualTo(4);
    }

    @Test
    void findAllWithRegionBySubCategory() {
        em.flush();
        em.clear();

        Pageable pageable = PageRequest.of(0, 4);
        OrderMethod orderMethod = OrderMethod.RECENT_ORDER;
        Regions regions = Regions.DAEJEON;

       // Page<Post> findPostList = qPostRepository.findAllWithRegionBySubCategory(pageable, orderMethod, regions, null);
        for(Post p : findPostList){
            log.info("{}, {}, {}, {}, {}, {}, {}, {}",
                    p.getUser().getId(),
                    p.getTitle(),
                    p.getUser().getUserId(),
                    p.getStartDate(),
                    p.getEndDate(),
                    p.getContent(),
                    p.getLikeNum(),
                    p.getCommentNum());
        }

        Assertions.assertThat(findPostList.getTotalElements()).isEqualTo(1);
    }

    @Test
    void findAllWithThemeBySubCategory() {
        em.flush();
        em.clear();

        Pageable pageable = PageRequest.of(0, 4);
        OrderMethod orderMethod = OrderMethod.RECENT_ORDER;
        Themes themes = Themes.SHOPPING;

        Page<Post> findPostList = qPostRepository.findAllBySubCategoryOrderByOrderMethod(pageable, orderMethod, themes, null);
        for(Post p : findPostList){
            log.info("{}, {}, {}, {}, {}, {}, {}, {}",
                    p.getUser().getId(),
                    p.getTitle(),
                    p.getUser().getUserId(),
                    p.getStartDate(),
                    p.getEndDate(),
                    p.getContent(),
                    p.getLikeNum(),
                    p.getCommentNum());
        }

        Assertions.assertThat(findPostList.getTotalElements()).isEqualTo(1);
    }

    @Test
    void findAllWithSeasonBySubCategory() {
        em.flush();
        em.clear();

        Pageable pageable = PageRequest.of(0, 4);
        OrderMethod orderMethod = OrderMethod.RECENT_ORDER;
        Seasons seasons = Seasons.SUMMER;

        Page<Post> findPostList = qPostRepository.findAllWithSeasonBySubCategory(pageable, orderMethod, seasons, null);
        for(Post p : findPostList){
            log.info("{}, {}, {}, {}, {}, {}, {}, {}",
                    p.getUser().getId(),
                    p.getTitle(),
                    p.getUser().getUserId(),
                    p.getStartDate(),
                    p.getEndDate(),
                    p.getContent(),
                    p.getLikeNum(),
                    p.getCommentNum());
        }

        Assertions.assertThat(findPostList.getTotalElements()).isEqualTo(2);
    }*/
}