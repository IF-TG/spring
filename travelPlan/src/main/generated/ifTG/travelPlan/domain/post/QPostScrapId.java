package ifTG.travelPlan.domain.post;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPostScrapId is a Querydsl query type for PostScrapId
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QPostScrapId extends BeanPath<PostScrapId> {

    private static final long serialVersionUID = -1870166437L;

    public static final QPostScrapId postScrapId = new QPostScrapId("postScrapId");

    public final NumberPath<Long> postId = createNumber("postId", Long.class);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QPostScrapId(String variable) {
        super(PostScrapId.class, forVariable(variable));
    }

    public QPostScrapId(Path<? extends PostScrapId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPostScrapId(PathMetadata metadata) {
        super(PostScrapId.class, metadata);
    }

}

