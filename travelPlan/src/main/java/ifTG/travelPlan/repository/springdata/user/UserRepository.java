package ifTG.travelPlan.repository.springdata.user;

import ifTG.travelPlan.domain.user.Sex;
import ifTG.travelPlan.domain.user.User;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * select all
     */


    /**
     * select all with page
     */
    Page<User> findAll(Pageable pageable);
    /**
     * select all with fetchJoin and page
     */
    /*@EntityGraph(attributePaths = "commentList")
    Page<User> findAllWithCommentListById(Long id, Pageable pageable);
    @Query("SELECT u FROM User u LEFT JOIN FETCH u.postList p WHERE u.id = :id")
    Page<User> findAllWithPostListById(Long id, Pageable pageable);
    @Query("SELECT u FROM User u LEFT JOIN FETCH u.postList p WHERE u.id = :userId")
    Page<User> findAllWithPostListByUserId(String userId, Pageable pageable);*/
    @EntityGraph(attributePaths = "travelPlan")
    Page<User> findAllWithTravelPlansById(Long id, Pageable pageable);


    /**
     * select
     */

    User findByNickname(String nickname);

    @Deprecated
    @Query("SELECT u FROM User u LEFT JOIN FETCH u.userBlockList ub LEFT JOIN FETCH u.postLikeList pl WHERE u.id = :id")
    User findByUserIdWithUserBlockAndPostLike(Long id);

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.postLikeList pl WHERE u.id = :userId")
    User findByUserIdWithPostLike(String userId);


    @Query("SELECT u FROM User u LEFT JOIN FETCH u.commentLikeList LEFT JOIN FETCH u.nestedCommentLikeList WHERE u.id = :id")
    User findWithCommentLikeAndNestedCommentLikeByUserId(Long id);

    @Query("SELECT u.profileImgUrl FROM User u WHERE u.id = :userId")
    Optional<String> findProfileImgByUserId(Long userId);

    Boolean existsByNickname(String nickname);

    @Modifying
    @Transactional
    @Query(value = "UPDATE users SET nickname = :nickname WHERE id = :userId", nativeQuery = true)
    void updateNickname(Long userId, String nickname);

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.postLikeList pl WHERE u.id = :userId")
    User findByIdWithPostLike(Long userId);
}
