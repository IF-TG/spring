package ifTG.travelPlan.repository.springdata.user;

import ifTG.travelPlan.domain.user.User;
import ifTG.travelPlan.domain.user.UserAddress;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserAddressRepository extends JpaRepository<UserAddress, Long> {
    /**
     * select all
     */
    Page<UserAddress> findAll(Pageable pageable);
    List<UserAddress> findAllBySido(String sido);

    /**
     * select one
     */


    /**
     * Fetch join
     */
    @Query("SELECT ua FROM UserAddress ua JOIN FETCH ua.user u WHERE u.id = :id")
    UserAddress findWithUserById(@Param("id") Long id);


    /**
     * Aggregate Function
     */
    @Query("SELECT us.sido, COUNT(us) FROM UserAddress us GROUP BY us.sido")
    List<Object[]> countGroupBySido();
}

