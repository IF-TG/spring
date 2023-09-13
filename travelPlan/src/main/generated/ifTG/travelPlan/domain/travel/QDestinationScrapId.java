package ifTG.travelPlan.domain.travel;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QDestinationScrapId is a Querydsl query type for DestinationScrapId
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QDestinationScrapId extends BeanPath<DestinationScrapId> {

    private static final long serialVersionUID = -1491071435L;

    public static final QDestinationScrapId destinationScrapId = new QDestinationScrapId("destinationScrapId");

    public final NumberPath<Long> destinationId = createNumber("destinationId", Long.class);

    public final NumberPath<Long> folderId = createNumber("folderId", Long.class);

    public QDestinationScrapId(String variable) {
        super(DestinationScrapId.class, forVariable(variable));
    }

    public QDestinationScrapId(Path<? extends DestinationScrapId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDestinationScrapId(PathMetadata metadata) {
        super(DestinationScrapId.class, metadata);
    }

}

