package ifTG.travelPlan.domain.travel.destinationdetail;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCulturalFacility is a Querydsl query type for CulturalFacility
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCulturalFacility extends EntityPathBase<CulturalFacility> {

    private static final long serialVersionUID = -532884405L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCulturalFacility culturalFacility = new QCulturalFacility("culturalFacility");

    public final StringPath capacity = createString("capacity");

    public final StringPath checkBabyStroller = createString("checkBabyStroller");

    public final StringPath checkPet = createString("checkPet");

    public final ifTG.travelPlan.domain.travel.QDestination destination;

    public final StringPath discountInfo = createString("discountInfo");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath parking = createString("parking");

    public final StringPath parkingFee = createString("parkingFee");

    public final StringPath scale = createString("scale");

    public final StringPath spendTime = createString("spendTime");

    public final StringPath usageFee = createString("usageFee");

    public final StringPath usageTime = createString("usageTime");

    public QCulturalFacility(String variable) {
        this(CulturalFacility.class, forVariable(variable), INITS);
    }

    public QCulturalFacility(Path<? extends CulturalFacility> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCulturalFacility(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCulturalFacility(PathMetadata metadata, PathInits inits) {
        this(CulturalFacility.class, metadata, inits);
    }

    public QCulturalFacility(Class<? extends CulturalFacility> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.destination = inits.isInitialized("destination") ? new ifTG.travelPlan.domain.travel.QDestination(forProperty("destination"), inits.get("destination")) : null;
    }

}

