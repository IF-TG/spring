package ifTG.travelPlan.domain.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = 1984392209L;

    public static final QUser user = new QUser("user");

    public final SetPath<ifTG.travelPlan.domain.post.comment.CommentLike, ifTG.travelPlan.domain.post.comment.QCommentLike> commentLikeList = this.<ifTG.travelPlan.domain.post.comment.CommentLike, ifTG.travelPlan.domain.post.comment.QCommentLike>createSet("commentLikeList", ifTG.travelPlan.domain.post.comment.CommentLike.class, ifTG.travelPlan.domain.post.comment.QCommentLike.class, PathInits.DIRECT2);

    public final ListPath<ifTG.travelPlan.domain.post.comment.Comment, ifTG.travelPlan.domain.post.comment.QComment> commentList = this.<ifTG.travelPlan.domain.post.comment.Comment, ifTG.travelPlan.domain.post.comment.QComment>createList("commentList", ifTG.travelPlan.domain.post.comment.Comment.class, ifTG.travelPlan.domain.post.comment.QComment.class, PathInits.DIRECT2);

    public final DateTimePath<java.time.LocalDateTime> createAt = createDateTime("createAt", java.time.LocalDateTime.class);

    public final ListPath<ifTG.travelPlan.domain.travel.DestinationScrap, ifTG.travelPlan.domain.travel.QDestinationScrap> destinationScrapList = this.<ifTG.travelPlan.domain.travel.DestinationScrap, ifTG.travelPlan.domain.travel.QDestinationScrap>createList("destinationScrapList", ifTG.travelPlan.domain.travel.DestinationScrap.class, ifTG.travelPlan.domain.travel.QDestinationScrap.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final SetPath<ifTG.travelPlan.domain.post.comment.NestedCommentLike, ifTG.travelPlan.domain.post.comment.QNestedCommentLike> nestedCommentLikeList = this.<ifTG.travelPlan.domain.post.comment.NestedCommentLike, ifTG.travelPlan.domain.post.comment.QNestedCommentLike>createSet("nestedCommentLikeList", ifTG.travelPlan.domain.post.comment.NestedCommentLike.class, ifTG.travelPlan.domain.post.comment.QNestedCommentLike.class, PathInits.DIRECT2);

    public final StringPath nickname = createString("nickname");

    public final ListPath<ifTG.travelPlan.domain.post.PostLike, ifTG.travelPlan.domain.post.QPostLike> postLikeList = this.<ifTG.travelPlan.domain.post.PostLike, ifTG.travelPlan.domain.post.QPostLike>createList("postLikeList", ifTG.travelPlan.domain.post.PostLike.class, ifTG.travelPlan.domain.post.QPostLike.class, PathInits.DIRECT2);

    public final ListPath<ifTG.travelPlan.domain.post.Post, ifTG.travelPlan.domain.post.QPost> postList = this.<ifTG.travelPlan.domain.post.Post, ifTG.travelPlan.domain.post.QPost>createList("postList", ifTG.travelPlan.domain.post.Post.class, ifTG.travelPlan.domain.post.QPost.class, PathInits.DIRECT2);

    public final ListPath<ifTG.travelPlan.domain.post.PostScrap, ifTG.travelPlan.domain.post.QPostScrap> postScrapList = this.<ifTG.travelPlan.domain.post.PostScrap, ifTG.travelPlan.domain.post.QPostScrap>createList("postScrapList", ifTG.travelPlan.domain.post.PostScrap.class, ifTG.travelPlan.domain.post.QPostScrap.class, PathInits.DIRECT2);

    public final StringPath profileImgUrl = createString("profileImgUrl");

    public final ListPath<SearchHistory, QSearchHistory> searchHistoryList = this.<SearchHistory, QSearchHistory>createList("searchHistoryList", SearchHistory.class, QSearchHistory.class, PathInits.DIRECT2);

    public final ListPath<ifTG.travelPlan.domain.travel.TravelPlan, ifTG.travelPlan.domain.travel.QTravelPlan> travelPlanList = this.<ifTG.travelPlan.domain.travel.TravelPlan, ifTG.travelPlan.domain.travel.QTravelPlan>createList("travelPlanList", ifTG.travelPlan.domain.travel.TravelPlan.class, ifTG.travelPlan.domain.travel.QTravelPlan.class, PathInits.DIRECT2);

    public final ListPath<UserBlock, QUserBlock> userBlockList = this.<UserBlock, QUserBlock>createList("userBlockList", UserBlock.class, QUserBlock.class, PathInits.DIRECT2);

    public final ListPath<UserLog, QUserLog> userLogList = this.<UserLog, QUserLog>createList("userLogList", UserLog.class, QUserLog.class, PathInits.DIRECT2);

    public final ListPath<UserVector, QUserVector> userVectorList = this.<UserVector, QUserVector>createList("userVectorList", UserVector.class, QUserVector.class, PathInits.DIRECT2);

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

