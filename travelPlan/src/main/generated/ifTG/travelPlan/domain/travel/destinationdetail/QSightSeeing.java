package ifTG.travelPlan.domain.travel.destinationdetail;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSightSeeing is a Querydsl query type for SightSeeing
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSightSeeing extends EntityPathBase<SightSeeing> {

    private static final long serialVersionUID = 1043534694L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSightSeeing sightSeeing = new QSightSeeing("sightSeeing");

    public final StringPath capacity = createString("capacity");

    public final StringPath checkBabyStroller = createString("checkBabyStroller");

    public final StringPath checkPet = createString("checkPet");

    public final ifTG.travelPlan.domain.travel.QDestination destination;

    public final StringPath experienceGuide = createString("experienceGuide");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath openDate = createString("openDate");

    public final StringPath restDate = createString("restDate");

    public final StringPath usageTime = createString("usageTime");

    public QSightSeeing(String variable) {
        this(SightSeeing.class, forVariable(variable), INITS);
    }

    public QSightSeeing(Path<? extends SightSeeing> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSightSeeing(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSightSeeing(PathMetadata metadata, PathInits inits) {
        this(SightSeeing.class, metadata, inits);
    }

    public QSightSeeing(Class<? extends SightSeeing> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.destination = inits.isInitialized("destination") ? new ifTG.travelPlan.domain.travel.QDestination(forProperty("destination"), inits.get("destination")) : null;
    }

}

