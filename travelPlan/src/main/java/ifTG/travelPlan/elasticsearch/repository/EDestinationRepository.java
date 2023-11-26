package ifTG.travelPlan.elasticsearch.repository;

import ifTG.travelPlan.elasticsearch.domain.EDestination;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface EDestinationRepository extends ElasticsearchRepository<EDestination, Long> {
}
