package ifTG.travelPlan.dto.travel.enums;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCategory is a Querydsl query type for Category
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QCategory extends BeanPath<Category> {

    private static final long serialVersionUID = -2089896814L;

    public static final QCategory category = new QCategory("category");

    public final EnumPath<LargeCategory> largeCategory = createEnum("largeCategory", LargeCategory.class);

    public final EnumPath<MiddleCategory> middleCategory = createEnum("middleCategory", MiddleCategory.class);

    public final EnumPath<SmallCategory> smallCategory = createEnum("smallCategory", SmallCategory.class);

    public QCategory(String variable) {
        super(Category.class, forVariable(variable));
    }

    public QCategory(Path<? extends Category> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCategory(PathMetadata metadata) {
        super(Category.class, metadata);
    }

}

