package ifTG.travelPlan.domain.travel;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDestinationImg is a Querydsl query type for DestinationImg
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDestinationImg extends EntityPathBase<DestinationImg> {

    private static final long serialVersionUID = 1357521324L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDestinationImg destinationImg = new QDestinationImg("destinationImg");

    public final QDestination destination;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imgUrl = createString("imgUrl");

    public final StringPath thumbnailImg = createString("thumbnailImg");

    public QDestinationImg(String variable) {
        this(DestinationImg.class, forVariable(variable), INITS);
    }

    public QDestinationImg(Path<? extends DestinationImg> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDestinationImg(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDestinationImg(PathMetadata metadata, PathInits inits) {
        this(DestinationImg.class, metadata, inits);
    }

    public QDestinationImg(Class<? extends DestinationImg> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.destination = inits.isInitialized("destination") ? new QDestination(forProperty("destination"), inits.get("destination")) : null;
    }

}

