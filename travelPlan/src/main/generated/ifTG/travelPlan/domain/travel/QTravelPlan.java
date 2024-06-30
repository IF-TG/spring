package ifTG.travelPlan.domain.travel;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTravelPlan is a Querydsl query type for TravelPlan
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTravelPlan extends EntityPathBase<TravelPlan> {

    private static final long serialVersionUID = -370225734L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTravelPlan travelPlan = new QTravelPlan("travelPlan");

    public final ListPath<ifTG.travelPlan.domain.diary.Diary, ifTG.travelPlan.domain.diary.QDiary> dairyList = this.<ifTG.travelPlan.domain.diary.Diary, ifTG.travelPlan.domain.diary.QDiary>createList("dairyList", ifTG.travelPlan.domain.diary.Diary.class, ifTG.travelPlan.domain.diary.QDiary.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath title = createString("title");

    public final ListPath<TravelPlanDestination, QTravelPlanDestination> travelPlanDestinationList = this.<TravelPlanDestination, QTravelPlanDestination>createList("travelPlanDestinationList", TravelPlanDestination.class, QTravelPlanDestination.class, PathInits.DIRECT2);

    public final ifTG.travelPlan.domain.user.QUser user;

    public QTravelPlan(String variable) {
        this(TravelPlan.class, forVariable(variable), INITS);
    }

    public QTravelPlan(Path<? extends TravelPlan> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTravelPlan(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTravelPlan(PathMetadata metadata, PathInits inits) {
        this(TravelPlan.class, metadata, inits);
    }

    public QTravelPlan(Class<? extends TravelPlan> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new ifTG.travelPlan.domain.user.QUser(forProperty("user")) : null;
    }

}

