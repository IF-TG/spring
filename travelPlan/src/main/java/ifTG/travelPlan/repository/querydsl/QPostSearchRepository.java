package ifTG.travelPlan.repository.querydsl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import ifTG.travelPlan.domain.post.Post;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

import static ifTG.travelPlan.domain.post.QPost.post;
import static ifTG.travelPlan.domain.user.QUser.user;


@Repository
@Getter
public class QPostSearchRepository {
    @PersistenceContext
    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    @Autowired
    public QPostSearchRepository(EntityManager em){
        this.em = em;
        queryFactory = new JPAQueryFactory(em);
    }

    public Page<Post> findAllByKeywordNotInBlockedUserId(Pageable pageable, String keyword, boolean isContent, boolean isTitle, List<Long> blockedUserId){
        BooleanBuilder booleanBuilder = getBooleanBuilder(keyword, isContent, isTitle);
        return findAllByKeywordNotInBlockedUserId(pageable, blockedUserId, booleanBuilder);
    }

    private static BooleanBuilder getBooleanBuilder(String keyword, boolean isContent, boolean isTitle) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if(isContent){
            booleanBuilder.or(post.content.like("%"+keyword+"%"));
        }
        if(isTitle){
            booleanBuilder.or(post.title.like("%"+keyword+"%"));
        }
        return booleanBuilder;
    }

    private Page<Post> findAllByKeywordNotInBlockedUserId(Pageable pageable, List<Long> blockedUserId, BooleanBuilder booleanBuilder) {
        List<Post> postList = queryFactory
                .selectFrom(post)
                .innerJoin(post.user, user).fetchJoin()
                .where(booleanBuilder.and(user.id.notIn(blockedUserId)))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(post.createAt.desc())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(post.count())
                .from(post)
                .where(booleanBuilder);

        return PageableExecutionUtils.getPage(postList, pageable, countQuery::fetchOne);
    }
}
