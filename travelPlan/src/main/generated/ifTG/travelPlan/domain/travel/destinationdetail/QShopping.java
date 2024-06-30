package ifTG.travelPlan.domain.travel.destinationdetail;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QShopping is a Querydsl query type for Shopping
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QShopping extends EntityPathBase<Shopping> {

    private static final long serialVersionUID = -947414290L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QShopping shopping = new QShopping("shopping");

    public final StringPath checkBabyStroller = createString("checkBabyStroller");

    public final StringPath checkPet = createString("checkPet");

    public final ifTG.travelPlan.domain.travel.QDestination destination;

    public final StringPath fairDate = createString("fairDate");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath openDate = createString("openDate");

    public final StringPath openTime = createString("openTime");

    public final StringPath restDate = createString("restDate");

    public final StringPath saleItem = createString("saleItem");

    public final StringPath scale = createString("scale");

    public QShopping(String variable) {
        this(Shopping.class, forVariable(variable), INITS);
    }

    public QShopping(Path<? extends Shopping> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QShopping(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QShopping(PathMetadata metadata, PathInits inits) {
        this(Shopping.class, metadata, inits);
    }

    public QShopping(Class<? extends Shopping> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.destination = inits.isInitialized("destination") ? new ifTG.travelPlan.domain.travel.QDestination(forProperty("destination"), inits.get("destination")) : null;
    }

}

