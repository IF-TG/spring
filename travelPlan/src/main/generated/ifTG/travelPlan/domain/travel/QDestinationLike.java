package ifTG.travelPlan.domain.travel;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDestinationLike is a Querydsl query type for DestinationLike
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDestinationLike extends EntityPathBase<DestinationLike> {

    private static final long serialVersionUID = -866426162L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDestinationLike destinationLike = new QDestinationLike("destinationLike");

    public final QDestination destination;

    public final QDestinationLikeId destinationLikeId;

    public final ifTG.travelPlan.domain.user.QUser user;

    public QDestinationLike(String variable) {
        this(DestinationLike.class, forVariable(variable), INITS);
    }

    public QDestinationLike(Path<? extends DestinationLike> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDestinationLike(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDestinationLike(PathMetadata metadata, PathInits inits) {
        this(DestinationLike.class, metadata, inits);
    }

    public QDestinationLike(Class<? extends DestinationLike> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.destination = inits.isInitialized("destination") ? new QDestination(forProperty("destination")) : null;
        this.destinationLikeId = inits.isInitialized("destinationLikeId") ? new QDestinationLikeId(forProperty("destinationLikeId")) : null;
        this.user = inits.isInitialized("user") ? new ifTG.travelPlan.domain.user.QUser(forProperty("user"), inits.get("user")) : null;
    }

}

