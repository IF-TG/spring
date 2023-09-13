package ifTG.travelPlan.domain.post.comment;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QNestedComment is a Querydsl query type for NestedComment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QNestedComment extends EntityPathBase<NestedComment> {

    private static final long serialVersionUID = -1991709784L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QNestedComment nestedComment = new QNestedComment("nestedComment");

    public final StringPath comment = createString("comment");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QComment parentComment;

    public final ifTG.travelPlan.domain.user.QUser user;

    public QNestedComment(String variable) {
        this(NestedComment.class, forVariable(variable), INITS);
    }

    public QNestedComment(Path<? extends NestedComment> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QNestedComment(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QNestedComment(PathMetadata metadata, PathInits inits) {
        this(NestedComment.class, metadata, inits);
    }

    public QNestedComment(Class<? extends NestedComment> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.parentComment = inits.isInitialized("parentComment") ? new QComment(forProperty("parentComment"), inits.get("parentComment")) : null;
        this.user = inits.isInitialized("user") ? new ifTG.travelPlan.domain.user.QUser(forProperty("user"), inits.get("user")) : null;
    }

}

