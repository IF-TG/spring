package ifTG.travelPlan.domain.post;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPostViewId is a Querydsl query type for PostViewId
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QPostViewId extends BeanPath<PostViewId> {

    private static final long serialVersionUID = 1970395953L;

    public static final QPostViewId postViewId = new QPostViewId("postViewId");

    public final NumberPath<Long> postId = createNumber("postId", Long.class);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QPostViewId(String variable) {
        super(PostViewId.class, forVariable(variable));
    }

    public QPostViewId(Path<? extends PostViewId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPostViewId(PathMetadata metadata) {
        super(PostViewId.class, metadata);
    }

}

