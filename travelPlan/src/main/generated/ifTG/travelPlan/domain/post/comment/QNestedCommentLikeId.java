package ifTG.travelPlan.domain.post.comment;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QNestedCommentLikeId is a Querydsl query type for NestedCommentLikeId
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QNestedCommentLikeId extends BeanPath<NestedCommentLikeId> {

    private static final long serialVersionUID = 174958810L;

    public static final QNestedCommentLikeId nestedCommentLikeId = new QNestedCommentLikeId("nestedCommentLikeId");

    public final NumberPath<Long> nestedCommentId = createNumber("nestedCommentId", Long.class);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QNestedCommentLikeId(String variable) {
        super(NestedCommentLikeId.class, forVariable(variable));
    }

    public QNestedCommentLikeId(Path<? extends NestedCommentLikeId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QNestedCommentLikeId(PathMetadata metadata) {
        super(NestedCommentLikeId.class, metadata);
    }

}

