package ifTG.travelPlan.domain.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserBlockId is a Querydsl query type for UserBlockId
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QUserBlockId extends BeanPath<UserBlockId> {

    private static final long serialVersionUID = -2102622921L;

    public static final QUserBlockId userBlockId = new QUserBlockId("userBlockId");

    public final NumberPath<Long> blockedUserId = createNumber("blockedUserId", Long.class);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QUserBlockId(String variable) {
        super(UserBlockId.class, forVariable(variable));
    }

    public QUserBlockId(Path<? extends UserBlockId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserBlockId(PathMetadata metadata) {
        super(UserBlockId.class, metadata);
    }

}

