package ifTG.travelPlan.repository.springdata.user;

import ifTG.travelPlan.domain.user.UserBlock;
import ifTG.travelPlan.domain.user.UserBlockId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserBlockRepository extends JpaRepository<UserBlock, UserBlockId> {
    @Query("SELECT ub FROM UserBlock ub WHERE ub.user.userId = :userId")
    List<UserBlock> findAllByUserId(String userId);
}
