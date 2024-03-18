package ifTG.travelPlan.repository.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import ifTG.travelPlan.service.api.dto.ContentType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import static ifTG.travelPlan.domain.travel.destinationdetail.QCulturalFacility.culturalFacility;
import static ifTG.travelPlan.domain.travel.destinationdetail.QEvent.event;
import static ifTG.travelPlan.domain.travel.destinationdetail.QLeisureSports.leisureSports;
import static ifTG.travelPlan.domain.travel.destinationdetail.QRestaurant.restaurant;
import static ifTG.travelPlan.domain.travel.destinationdetail.QShopping.shopping;
import static ifTG.travelPlan.domain.travel.destinationdetail.QSightSeeing.sightSeeing;


@Repository
public class QDestinationRepository {
    @PersistenceContext
    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    @Autowired
    public QDestinationRepository(EntityManager em) {
        this.em = em;
        queryFactory = new JPAQueryFactory(em);
    }

    public Object findDetailWithDestinationById(Long destinationId, ContentType contentType){
        Object result = null;
        switch(contentType){
            case Cultural_Facility -> result = queryFactory.selectFrom(culturalFacility).join(culturalFacility.destination).fetchJoin().where(culturalFacility.destination.id.eq(destinationId)).fetchOne();
            case Event_Performance_Festival -> result = queryFactory.selectFrom(event).join(event.destination).fetchJoin().where(event.destination.id.eq(destinationId)).fetchOne();
            case LeisureSports -> result = queryFactory.selectFrom(leisureSports).join(leisureSports.destination).fetchJoin().where(leisureSports.destination.id.eq(destinationId)).fetchOne();
            case Restaurant -> result = queryFactory.selectFrom(restaurant).join(restaurant.destination).fetchJoin().where(restaurant.destination.id.eq(destinationId)).fetchOne();
            case Shopping -> result = queryFactory.selectFrom(shopping).join(shopping.destination).fetchJoin().where(shopping.destination.id.eq(destinationId)).fetchOne();
            case Sightseeing -> result = queryFactory.selectFrom(sightSeeing).join(sightSeeing.destination).fetchJoin().where(sightSeeing.destination.id.eq(destinationId)).fetchOne();
        }
        return result;
    }
}
