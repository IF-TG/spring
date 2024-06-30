package ifTG.travelPlan.domain.travel.destinationdetail;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRestaurant is a Querydsl query type for Restaurant
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRestaurant extends EntityPathBase<Restaurant> {

    private static final long serialVersionUID = -1390040253L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRestaurant restaurant = new QRestaurant("restaurant");

    public final ifTG.travelPlan.domain.travel.QDestination destination;

    public final StringPath featuredMenu = createString("featuredMenu");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath openDate = createString("openDate");

    public final StringPath openTime = createString("openTime");

    public final StringPath packing = createString("packing");

    public final StringPath parking = createString("parking");

    public final StringPath restDate = createString("restDate");

    public final StringPath scale = createString("scale");

    public final StringPath seat = createString("seat");

    public final StringPath treatMenu = createString("treatMenu");

    public QRestaurant(String variable) {
        this(Restaurant.class, forVariable(variable), INITS);
    }

    public QRestaurant(Path<? extends Restaurant> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRestaurant(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRestaurant(PathMetadata metadata, PathInits inits) {
        this(Restaurant.class, metadata, inits);
    }

    public QRestaurant(Class<? extends Restaurant> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.destination = inits.isInitialized("destination") ? new ifTG.travelPlan.domain.travel.QDestination(forProperty("destination"), inits.get("destination")) : null;
    }

}

