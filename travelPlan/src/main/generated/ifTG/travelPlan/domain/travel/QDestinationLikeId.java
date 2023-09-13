package ifTG.travelPlan.domain.travel;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QDestinationLikeId is a Querydsl query type for DestinationLikeId
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QDestinationLikeId extends BeanPath<DestinationLikeId> {

    private static final long serialVersionUID = 588116105L;

    public static final QDestinationLikeId destinationLikeId = new QDestinationLikeId("destinationLikeId");

    public final NumberPath<Long> destinationId = createNumber("destinationId", Long.class);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QDestinationLikeId(String variable) {
        super(DestinationLikeId.class, forVariable(variable));
    }

    public QDestinationLikeId(Path<? extends DestinationLikeId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDestinationLikeId(PathMetadata metadata) {
        super(DestinationLikeId.class, metadata);
    }

}

