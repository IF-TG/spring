package ifTG.travelPlan.domain.post;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPost is a Querydsl query type for Post
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPost extends EntityPathBase<Post> {

    private static final long serialVersionUID = -647288527L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPost post = new QPost("post");

    public final ListPath<ifTG.travelPlan.domain.post.comment.Comment, ifTG.travelPlan.domain.post.comment.QComment> commentList = this.<ifTG.travelPlan.domain.post.comment.Comment, ifTG.travelPlan.domain.post.comment.QComment>createList("commentList", ifTG.travelPlan.domain.post.comment.Comment.class, ifTG.travelPlan.domain.post.comment.QComment.class, PathInits.DIRECT2);

    public final NumberPath<Integer> commentNum = createNumber("commentNum", Integer.class);

    public final StringPath content = createString("content");

    public final DateTimePath<java.time.LocalDateTime> createAt = createDateTime("createAt", java.time.LocalDateTime.class);

    public final DatePath<java.time.LocalDate> endDate = createDate("endDate", java.time.LocalDate.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> likeNum = createNumber("likeNum", Integer.class);

    public final NumberPath<Double> mapX = createNumber("mapX", Double.class);

    public final NumberPath<Double> mapY = createNumber("mapY", Double.class);

    public final SetPath<PostCategory, QPostCategory> postCategoryList = this.<PostCategory, QPostCategory>createSet("postCategoryList", PostCategory.class, QPostCategory.class, PathInits.DIRECT2);

    public final ListPath<PostImg, QPostImg> postImgList = this.<PostImg, QPostImg>createList("postImgList", PostImg.class, QPostImg.class, PathInits.DIRECT2);

    public final ListPath<PostLike, QPostLike> postLikeList = this.<PostLike, QPostLike>createList("postLikeList", PostLike.class, QPostLike.class, PathInits.DIRECT2);

    public final ListPath<PostScrap, QPostScrap> postScrapList = this.<PostScrap, QPostScrap>createList("postScrapList", PostScrap.class, QPostScrap.class, PathInits.DIRECT2);

    public final ListPath<PostView, QPostView> postViewList = this.<PostView, QPostView>createList("postViewList", PostView.class, QPostView.class, PathInits.DIRECT2);

    public final NumberPath<Double> score = createNumber("score", Double.class);

    public final DatePath<java.time.LocalDate> startDate = createDate("startDate", java.time.LocalDate.class);

    public final StringPath title = createString("title");

    public final ifTG.travelPlan.domain.user.QUser user;

    public QPost(String variable) {
        this(Post.class, forVariable(variable), INITS);
    }

    public QPost(Path<? extends Post> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPost(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPost(PathMetadata metadata, PathInits inits) {
        this(Post.class, metadata, inits);
    }

    public QPost(Class<? extends Post> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new ifTG.travelPlan.domain.user.QUser(forProperty("user")) : null;
    }

}

