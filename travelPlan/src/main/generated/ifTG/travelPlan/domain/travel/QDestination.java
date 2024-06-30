package ifTG.travelPlan.domain.travel;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDestination is a Querydsl query type for Destination
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDestination extends EntityPathBase<Destination> {

    private static final long serialVersionUID = 317219479L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDestination destination = new QDestination("destination");

    public final StringPath address = createString("address");

    public final StringPath addressDetail = createString("addressDetail");

    public final NumberPath<Integer> areaCode = createNumber("areaCode", Integer.class);

    public final ifTG.travelPlan.dto.travel.enums.QCategory category;

    public final EnumPath<ifTG.travelPlan.service.api.dto.ContentType> contentType = createEnum("contentType", ifTG.travelPlan.service.api.dto.ContentType.class);

    public final ListPath<DestinationImg, QDestinationImg> destinationImgList = this.<DestinationImg, QDestinationImg>createList("destinationImgList", DestinationImg.class, QDestinationImg.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Double> mapX = createNumber("mapX", Double.class);

    public final NumberPath<Double> mapY = createNumber("mapY", Double.class);

    public final NumberPath<Integer> mLevel = createNumber("mLevel", Integer.class);

    public final StringPath overview = createString("overview");

    public final StringPath tel = createString("tel");

    public final StringPath thumbNail = createString("thumbNail");

    public final StringPath title = createString("title");

    public final NumberPath<Long> tourApiContentId = createNumber("tourApiContentId", Long.class);

    public final StringPath zipcode = createString("zipcode");

    public QDestination(String variable) {
        this(Destination.class, forVariable(variable), INITS);
    }

    public QDestination(Path<? extends Destination> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDestination(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDestination(PathMetadata metadata, PathInits inits) {
        this(Destination.class, metadata, inits);
    }

    public QDestination(Class<? extends Destination> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.category = inits.isInitialized("category") ? new ifTG.travelPlan.dto.travel.enums.QCategory(forProperty("category")) : null;
    }

}

