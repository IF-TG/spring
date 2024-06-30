package ifTG.travelPlan.domain.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserVector is a Querydsl query type for UserVector
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserVector extends EntityPathBase<UserVector> {

    private static final long serialVersionUID = -1580259148L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserVector userVector = new QUserVector("userVector");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QUser user;

    public final MapPath<Integer, Double, NumberPath<Double>> vector = this.<Integer, Double, NumberPath<Double>>createMap("vector", Integer.class, Double.class, NumberPath.class);

    public QUserVector(String variable) {
        this(UserVector.class, forVariable(variable), INITS);
    }

    public QUserVector(Path<? extends UserVector> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserVector(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserVector(PathMetadata metadata, PathInits inits) {
        this(UserVector.class, metadata, inits);
    }

    public QUserVector(Class<? extends UserVector> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

