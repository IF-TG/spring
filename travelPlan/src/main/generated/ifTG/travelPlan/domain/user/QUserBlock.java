package ifTG.travelPlan.domain.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserBlock is a Querydsl query type for UserBlock
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserBlock extends EntityPathBase<UserBlock> {

    private static final long serialVersionUID = -900510980L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserBlock userBlock = new QUserBlock("userBlock");

    public final QUser blockedUser;

    public final QUser user;

    public final QUserBlockId userBlockId;

    public QUserBlock(String variable) {
        this(UserBlock.class, forVariable(variable), INITS);
    }

    public QUserBlock(Path<? extends UserBlock> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserBlock(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserBlock(PathMetadata metadata, PathInits inits) {
        this(UserBlock.class, metadata, inits);
    }

    public QUserBlock(Class<? extends UserBlock> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.blockedUser = inits.isInitialized("blockedUser") ? new QUser(forProperty("blockedUser"), inits.get("blockedUser")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user"), inits.get("user")) : null;
        this.userBlockId = inits.isInitialized("userBlockId") ? new QUserBlockId(forProperty("userBlockId")) : null;
    }

}

