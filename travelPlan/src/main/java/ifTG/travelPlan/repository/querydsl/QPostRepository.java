package ifTG.travelPlan.repository.querydsl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import ifTG.travelPlan.domain.post.Post;
import ifTG.travelPlan.domain.user.Sex;
import ifTG.travelPlan.domain.user.User;
import ifTG.travelPlan.domain.user.UserAddress;
import ifTG.travelPlan.dto.post.enums.*;
import ifTG.travelPlan.repository.springdata.user.UserBlockRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static ifTG.travelPlan.domain.post.QPost.post;
import static ifTG.travelPlan.domain.post.QPostImg.postImg;
import static ifTG.travelPlan.domain.user.QUser.user;


/**
 * 코드 중복성이 너무 크다
 * 나중에 이것을 어떻게 통합해보아야한다.
 */
@Repository
@Getter
@Slf4j
public class QPostRepository {
    @PersistenceContext
    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    @Autowired
    public QPostRepository(EntityManager em){
        this.em = em;
        queryFactory = new JPAQueryFactory(em);
    }

    public Page<Post> findAllBySubCategoryOrderByOrderMethod(Pageable pageable, OrderMethod orderMethod, Enum<?> subCategory, List<Long> blockedUserIdList) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if(subCategory != null) {
            booleanBuilder.and(post.postCategoryList.any().subCategory.eq(subCategory.toString()));
        }
        List<OrderSpecifier<?>> orderSpecifiers = getOrderSpecifiersFromPost(orderMethod);
        return findAllBySubCategory(pageable, booleanBuilder, orderSpecifiers, blockedUserIdList);
    }

    private static List<OrderSpecifier<?>> getOrderSpecifiersFromPost(OrderMethod orderMethod) {
        List<OrderSpecifier<?>> orderSpecifiers = new ArrayList<>();

        if (orderMethod.equals(OrderMethod.RECENT_ORDER)){
            orderSpecifiers.add(new OrderSpecifier<>(Order.DESC, post.createAt));
        }else if(orderMethod.equals(OrderMethod.RECOMMEND_ORDER)){
            orderSpecifiers.add(new OrderSpecifier<>(Order.DESC, post.likeNum));
            orderSpecifiers.add(new OrderSpecifier<>(Order.DESC, post.createAt));
        }
        return orderSpecifiers;
    }


    private Page<Post> findAllBySubCategory(Pageable pageable, BooleanBuilder booleanBuilder, List<OrderSpecifier<?>> orderSpecifiers, List<Long> blockedUserIdList) {
        List<Post> findPostList = queryFactory
                .select(post)
                .from(post)
                .innerJoin(post.user, user).fetchJoin()
                .where(booleanBuilder.and(user.id.notIn(blockedUserIdList)))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(orderSpecifiers.toArray(new OrderSpecifier<?>[0]))
                .distinct()
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(post.count())
                .from(post)
                .leftJoin(post.user)
                .join(post.postImgList, postImg)
                .where(booleanBuilder);

        return PageableExecutionUtils.getPage(findPostList, pageable, countQuery::fetchOne);

    }



}
