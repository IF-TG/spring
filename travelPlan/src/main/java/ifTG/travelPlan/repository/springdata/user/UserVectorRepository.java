package ifTG.travelPlan.repository.springdata.user;

import ifTG.travelPlan.domain.user.UserVector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserVectorRepository extends JpaRepository<UserVector, Long> {

    @Query("SELECT uv FROM UserVector uv WHERE uv.user.id = :userId")
    Optional<UserVector> findByUserId(Long userId);
}
