package ifTG.travelPlan.domain.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserLog is a Querydsl query type for UserLog
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserLog extends EntityPathBase<UserLog> {

    private static final long serialVersionUID = 1098512755L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserLog userLog = new QUserLog("userLog");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.time.LocalDateTime> loginTime = createDateTime("loginTime", java.time.LocalDateTime.class);

    public final QUser user;

    public QUserLog(String variable) {
        this(UserLog.class, forVariable(variable), INITS);
    }

    public QUserLog(Path<? extends UserLog> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserLog(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserLog(PathMetadata metadata, PathInits inits) {
        this(UserLog.class, metadata, inits);
    }

    public QUserLog(Class<? extends UserLog> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user"), inits.get("user")) : null;
    }

}

