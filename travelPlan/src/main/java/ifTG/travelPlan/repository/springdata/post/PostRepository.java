package ifTG.travelPlan.repository.springdata.post;

import ifTG.travelPlan.domain.post.Post;
import ifTG.travelPlan.domain.user.UserAddress;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    /**
     * select all
     */
    @Query("SELECT p FROM Post p ORDER BY p.createAt DESC")
    Page<Post> findAll(Pageable pageable);

    @Query(value = "SELECT p FROM Post p JOIN FETCH p.user u ORDER BY p.createAt DESC ",
        countQuery = "SELECT count(p) FROM Post p")
    Page<Post> findAllWithUser(Pageable pageable);

    @Query(value = "SELECT p FROM Post p JOIN FETCH p.user u WHERE u.userId = :userId ORDER BY p.createAt DESC ",
            countQuery = "SELECT count(p) FROM Post p WHERE p.user.userId = :userId")
    Page<Post> findAllWithUserByUserId(@Param("userId") String userId, Pageable pageable);

    @Query(value = "SELECT p FROM Post p JOIN FETCH p.user u WHERE u.nickname = :nickname ORDER BY p.createAt DESC ",
            countQuery = "SELECT count(p) FROM Post p WHERE p.user.nickname = :nickname")
    Page<Post> findAllWithUserByUserNickname(@Param("nickname") String nickname, Pageable pageable);

    @Query(value = "SELECT p FROM Post p WHERE p.id NOT IN :blockedIds AND p.title LIKE %:title% ORDER BY p.createAt DESC",
        countQuery = "SELECT COUNT(p) FROM Post p WHERE p.id NOT IN :blockedIds AND p.title LIKE %:title%")
    Page<Post> findAllLikeTitleNotInBlockedPost(@Param("title") String title, @Param("blockedIds") List<Long> blockedPostIdList, Pageable pageable);

    @Query(value = "SELECT p FROM Post p WHERE NOT IN :blockedIds AND p.content LIKE %:content% ORDER BY p.createAt DESC",
        countQuery = "SELECT COUNT(p) FROM Post p WHERE NOT IN :blockedIds AND p.content LIKE %:content%")
    Page<Post> findAllLikeContentNotInBlockedPost(@Param("content")String content, @Param("blockedIds") List<Long> blockedUserIdList, Pageable pageable);

    @Query(value = "SELECT p FROM Post p WHERE NOT IN :blockedIds AND (p.content LIKE %:keyword% OR p.title LIKE %:keyword%) ORDER BY p.createAt DESC",
            countQuery = "SELECT COUNT(p) FROM Post p WHERE NOT IN :blockedIds AND (p.content LIKE %:keyword% OR p.title LIKE %:keyword%)")
    Page<Post> findAllLikeContentAndTitleNotInBlockedPost(@Param("keyword")String keyword, @Param("blockedIds") List<Long> blockedUserIdList, Pageable pageable);

    /**
     * select one
     */
    @Query("SELECT p FROM Post p JOIN FETCH p.user LEFT JOIN FETCH p.postImgList LEFT JOIN FETCH p.postCategoryList WHERE p.id = :postId")
    Optional<Post> findByIdWithUserAndPostImgAndPostCategory(Long postId);

}
