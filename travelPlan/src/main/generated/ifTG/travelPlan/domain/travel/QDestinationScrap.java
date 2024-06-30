package ifTG.travelPlan.domain.travel;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDestinationScrap is a Querydsl query type for DestinationScrap
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDestinationScrap extends EntityPathBase<DestinationScrap> {

    private static final long serialVersionUID = -1083114630L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDestinationScrap destinationScrap = new QDestinationScrap("destinationScrap");

    public final QDestination destination;

    public final QDestinationScrapId destinationLikeId;

    public final StringPath folderName = createString("folderName");

    public final ifTG.travelPlan.domain.user.QUser user;

    public QDestinationScrap(String variable) {
        this(DestinationScrap.class, forVariable(variable), INITS);
    }

    public QDestinationScrap(Path<? extends DestinationScrap> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDestinationScrap(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDestinationScrap(PathMetadata metadata, PathInits inits) {
        this(DestinationScrap.class, metadata, inits);
    }

    public QDestinationScrap(Class<? extends DestinationScrap> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.destination = inits.isInitialized("destination") ? new QDestination(forProperty("destination"), inits.get("destination")) : null;
        this.destinationLikeId = inits.isInitialized("destinationLikeId") ? new QDestinationScrapId(forProperty("destinationLikeId")) : null;
        this.user = inits.isInitialized("user") ? new ifTG.travelPlan.domain.user.QUser(forProperty("user")) : null;
    }

}

