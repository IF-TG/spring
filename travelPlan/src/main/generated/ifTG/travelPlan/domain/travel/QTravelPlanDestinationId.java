package ifTG.travelPlan.domain.travel;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QTravelPlanDestinationId is a Querydsl query type for TravelPlanDestinationId
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QTravelPlanDestinationId extends BeanPath<TravelPlanDestinationId> {

    private static final long serialVersionUID = 542081583L;

    public static final QTravelPlanDestinationId travelPlanDestinationId = new QTravelPlanDestinationId("travelPlanDestinationId");

    public final NumberPath<Long> destinationId = createNumber("destinationId", Long.class);

    public final NumberPath<Long> travelPlanId = createNumber("travelPlanId", Long.class);

    public QTravelPlanDestinationId(String variable) {
        super(TravelPlanDestinationId.class, forVariable(variable));
    }

    public QTravelPlanDestinationId(Path<? extends TravelPlanDestinationId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTravelPlanDestinationId(PathMetadata metadata) {
        super(TravelPlanDestinationId.class, metadata);
    }

}

