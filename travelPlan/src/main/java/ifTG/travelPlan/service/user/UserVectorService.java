package ifTG.travelPlan.service.user;

import ifTG.travelPlan.domain.user.UserVector;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

public interface UserVectorService {
    @Transactional
    UserVector initUserVector(Long userId);

    @Transactional
    UserVector updateUserVector(UserVector userVector, double[] targetVector);
}
