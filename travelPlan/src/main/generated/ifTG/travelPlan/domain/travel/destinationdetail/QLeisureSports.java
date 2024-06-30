package ifTG.travelPlan.domain.travel.destinationdetail;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLeisureSports is a Querydsl query type for LeisureSports
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLeisureSports extends EntityPathBase<LeisureSports> {

    private static final long serialVersionUID = -1937357762L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLeisureSports leisureSports = new QLeisureSports("leisureSports");

    public final StringPath capacity = createString("capacity");

    public final StringPath checkBabyStroller = createString("checkBabyStroller");

    public final StringPath checkPet = createString("checkPet");

    public final ifTG.travelPlan.domain.travel.QDestination destination;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath openPeriod = createString("openPeriod");

    public final StringPath parking = createString("parking");

    public final StringPath parkingFee = createString("parkingFee");

    public final StringPath recommendedAge = createString("recommendedAge");

    public final StringPath usageFee = createString("usageFee");

    public final StringPath usageTime = createString("usageTime");

    public QLeisureSports(String variable) {
        this(LeisureSports.class, forVariable(variable), INITS);
    }

    public QLeisureSports(Path<? extends LeisureSports> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLeisureSports(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLeisureSports(PathMetadata metadata, PathInits inits) {
        this(LeisureSports.class, metadata, inits);
    }

    public QLeisureSports(Class<? extends LeisureSports> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.destination = inits.isInitialized("destination") ? new ifTG.travelPlan.domain.travel.QDestination(forProperty("destination"), inits.get("destination")) : null;
    }

}

