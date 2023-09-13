package ifTG.travelPlan.domain.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QScrapFolder is a Querydsl query type for ScrapFolder
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QScrapFolder extends EntityPathBase<ScrapFolder> {

    private static final long serialVersionUID = -161729383L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QScrapFolder scrapFolder = new QScrapFolder("scrapFolder");

    public final ListPath<ifTG.travelPlan.domain.travel.DestinationScrap, ifTG.travelPlan.domain.travel.QDestinationScrap> destinationScrapList = this.<ifTG.travelPlan.domain.travel.DestinationScrap, ifTG.travelPlan.domain.travel.QDestinationScrap>createList("destinationScrapList", ifTG.travelPlan.domain.travel.DestinationScrap.class, ifTG.travelPlan.domain.travel.QDestinationScrap.class, PathInits.DIRECT2);

    public final StringPath folderName = createString("folderName");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<ifTG.travelPlan.domain.post.PostScrap, ifTG.travelPlan.domain.post.QPostScrap> postScrapList = this.<ifTG.travelPlan.domain.post.PostScrap, ifTG.travelPlan.domain.post.QPostScrap>createList("postScrapList", ifTG.travelPlan.domain.post.PostScrap.class, ifTG.travelPlan.domain.post.QPostScrap.class, PathInits.DIRECT2);

    public final QUser user;

    public QScrapFolder(String variable) {
        this(ScrapFolder.class, forVariable(variable), INITS);
    }

    public QScrapFolder(Path<? extends ScrapFolder> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QScrapFolder(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QScrapFolder(PathMetadata metadata, PathInits inits) {
        this(ScrapFolder.class, metadata, inits);
    }

    public QScrapFolder(Class<? extends ScrapFolder> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user"), inits.get("user")) : null;
    }

}

