package ifTG.travelPlan.domain.post.comment;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QNestedCommentLike is a Querydsl query type for NestedCommentLike
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QNestedCommentLike extends EntityPathBase<NestedCommentLike> {

    private static final long serialVersionUID = -1640039585L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QNestedCommentLike nestedCommentLike = new QNestedCommentLike("nestedCommentLike");

    public final QNestedComment nestedComment;

    public final QNestedCommentLikeId nestedCommentLikeId;

    public final ifTG.travelPlan.domain.user.QUser user;

    public QNestedCommentLike(String variable) {
        this(NestedCommentLike.class, forVariable(variable), INITS);
    }

    public QNestedCommentLike(Path<? extends NestedCommentLike> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QNestedCommentLike(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QNestedCommentLike(PathMetadata metadata, PathInits inits) {
        this(NestedCommentLike.class, metadata, inits);
    }

    public QNestedCommentLike(Class<? extends NestedCommentLike> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.nestedComment = inits.isInitialized("nestedComment") ? new QNestedComment(forProperty("nestedComment"), inits.get("nestedComment")) : null;
        this.nestedCommentLikeId = inits.isInitialized("nestedCommentLikeId") ? new QNestedCommentLikeId(forProperty("nestedCommentLikeId")) : null;
        this.user = inits.isInitialized("user") ? new ifTG.travelPlan.domain.user.QUser(forProperty("user"), inits.get("user")) : null;
    }

}

