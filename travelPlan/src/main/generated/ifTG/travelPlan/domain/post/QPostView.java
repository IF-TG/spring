package ifTG.travelPlan.domain.post;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPostView is a Querydsl query type for PostView
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPostView extends EntityPathBase<PostView> {

    private static final long serialVersionUID = 1888081782L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPostView postView = new QPostView("postView");

    public final QPost post;

    public final QPostViewId postViewId;

    public final ifTG.travelPlan.domain.user.QUser user;

    public QPostView(String variable) {
        this(PostView.class, forVariable(variable), INITS);
    }

    public QPostView(Path<? extends PostView> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPostView(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPostView(PathMetadata metadata, PathInits inits) {
        this(PostView.class, metadata, inits);
    }

    public QPostView(Class<? extends PostView> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.post = inits.isInitialized("post") ? new QPost(forProperty("post"), inits.get("post")) : null;
        this.postViewId = inits.isInitialized("postViewId") ? new QPostViewId(forProperty("postViewId")) : null;
        this.user = inits.isInitialized("user") ? new ifTG.travelPlan.domain.user.QUser(forProperty("user"), inits.get("user")) : null;
    }

}

