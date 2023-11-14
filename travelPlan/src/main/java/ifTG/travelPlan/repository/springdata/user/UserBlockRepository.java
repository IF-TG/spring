package ifTG.travelPlan.repository.springdata.user;

import ifTG.travelPlan.domain.user.UserBlock;
import ifTG.travelPlan.domain.user.UserBlockId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserBlockRepository extends JpaRepository<UserBlock, UserBlockId> {
    @Query("SELECT ub FROM UserBlock ub JOIN FETCH ub.user u WHERE ub.user.id = :userId")
    List<UserBlock> findAllWithUserByUserId(Long userId);

    @Query("SELECT ub.blockedUser.id FROM UserBlock ub WHERE ub.user.id = :userId")
    List<Long> findBlockedUserIdListByUserId(Long userId);

    @Query("SELECT ub.user.id FROM UserBlock ub WHERE ub.blockedUser.id = :userId")
    List<Long> findUserIdListByBlockedUserId(Long userId);

    @Query("SELECT ub.user.id FROM UserBlock ub WHERE ub.blockedUser.id = :userId OR ub.user.id = :userId")
    List<Long> findUserIdListByBlockedUserIdAndBlockingUserId(Long userId);
}
