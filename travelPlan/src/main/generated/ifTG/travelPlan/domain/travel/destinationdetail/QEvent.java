package ifTG.travelPlan.domain.travel.destinationdetail;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QEvent is a Querydsl query type for Event
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QEvent extends EntityPathBase<Event> {

    private static final long serialVersionUID = -223041836L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QEvent event = new QEvent("event");

    public final StringPath ageLimit = createString("ageLimit");

    public final ifTG.travelPlan.domain.travel.QDestination destination;

    public final DatePath<java.time.LocalDate> endDate = createDate("endDate", java.time.LocalDate.class);

    public final StringPath eventPlace = createString("eventPlace");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath program = createString("program");

    public final StringPath showtime = createString("showtime");

    public final StringPath spendTime = createString("spendTime");

    public final StringPath sponsor = createString("sponsor");

    public final DatePath<java.time.LocalDate> startDate = createDate("startDate", java.time.LocalDate.class);

    public final StringPath usageFee = createString("usageFee");

    public QEvent(String variable) {
        this(Event.class, forVariable(variable), INITS);
    }

    public QEvent(Path<? extends Event> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QEvent(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QEvent(PathMetadata metadata, PathInits inits) {
        this(Event.class, metadata, inits);
    }

    public QEvent(Class<? extends Event> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.destination = inits.isInitialized("destination") ? new ifTG.travelPlan.domain.travel.QDestination(forProperty("destination"), inits.get("destination")) : null;
    }

}

