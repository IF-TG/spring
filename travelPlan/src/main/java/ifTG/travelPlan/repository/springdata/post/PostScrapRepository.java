package ifTG.travelPlan.repository.springdata.post;

import ifTG.travelPlan.domain.post.PostScrap;
import ifTG.travelPlan.domain.post.PostScrapId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostScrapRepository extends JpaRepository<PostScrap, PostScrapId> {
}
