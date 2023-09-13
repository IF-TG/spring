package ifTG.travelPlan.domain.travel;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QDestination is a Querydsl query type for Destination
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDestination extends EntityPathBase<Destination> {

    private static final long serialVersionUID = 317219479L;

    public static final QDestination destination = new QDestination("destination");

    public final StringPath destinationName = createString("destinationName");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> likes = createNumber("likes", Integer.class);

    public final StringPath location = createString("location");

    public QDestination(String variable) {
        super(Destination.class, forVariable(variable));
    }

    public QDestination(Path<? extends Destination> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDestination(PathMetadata metadata) {
        super(Destination.class, metadata);
    }

}

