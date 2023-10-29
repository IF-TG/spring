package ifTG.travelPlan.domain.travel;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTravelPlanDestination is a Querydsl query type for TravelPlanDestination
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTravelPlanDestination extends EntityPathBase<TravelPlanDestination> {

    private static final long serialVersionUID = 469837300L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTravelPlanDestination travelPlanDestination = new QTravelPlanDestination("travelPlanDestination");

    public final QDestination destination;

    public final DateTimePath<java.time.LocalDateTime> eta = createDateTime("eta", java.time.LocalDateTime.class);

    public final QTravelPlanDestinationId id;

    public final QTravelPlan travelPlan;

    public QTravelPlanDestination(String variable) {
        this(TravelPlanDestination.class, forVariable(variable), INITS);
    }

    public QTravelPlanDestination(Path<? extends TravelPlanDestination> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTravelPlanDestination(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTravelPlanDestination(PathMetadata metadata, PathInits inits) {
        this(TravelPlanDestination.class, metadata, inits);
    }

    public QTravelPlanDestination(Class<? extends TravelPlanDestination> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.destination = inits.isInitialized("destination") ? new QDestination(forProperty("destination")) : null;
        this.id = inits.isInitialized("id") ? new QTravelPlanDestinationId(forProperty("id")) : null;
        this.travelPlan = inits.isInitialized("travelPlan") ? new QTravelPlan(forProperty("travelPlan"), inits.get("travelPlan")) : null;
    }

}

