package ifTG.travelPlan.repository.springdata.post;

import ifTG.travelPlan.domain.post.Post;
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

    @Query(value = "SELECT p FROM Post p JOIN FETCH p.user u WHERE u.id = :id ORDER BY p.createAt DESC ",
            countQuery = "SELECT count(p) FROM Post p WHERE p.user.id = :id")
    Page<Post> findAllWithUserByUserId(@Param("id") Long id, Pageable pageable);

    @Query(value = "SELECT p FROM Post p JOIN FETCH p.user u WHERE u.nickname = :nickname ORDER BY p.createAt DESC ",
            countQuery = "SELECT count(p) FROM Post p WHERE p.user.nickname = :nickname")
    Page<Post> findAllWithUserByUserNickname(@Param("nickname") String nickname, Pageable pageable);


    /**
     * select one
     */
    @Query("SELECT p FROM Post p JOIN FETCH p.user LEFT JOIN FETCH p.postImgList LEFT JOIN FETCH p.postCategoryList WHERE p.id = :postId")
    Optional<Post> findByIdWithUserAndPostImgAndPostCategory(Long postId);


    @Query(value = "SELECT DISTINCT p FROM Post p JOIN FETCH p.user WHERE p.user.id NOT IN :blockUserList AND (p.id IN (SELECT c.post.id FROM Comment c WHERE c.user.id = :userId) OR p.id IN (SELECT pl.post.id FROM PostLike pl WHERE pl.user.id = :userId))",
        countQuery = "SELECT COUNT(DISTINCT p) FROM Post p WHERE p.user.id NOT IN :blockUserList AND (p.id IN (SELECT c.post.id FROM Comment c WHERE c.user.id = :userId) OR p.id IN (SELECT pl.post.id FROM PostLike pl WHERE pl.user.id = :userId))")
    Page<Post> findCommentedOrLikedPostListNotInBlockUserByUserId(Long userId, List<Long> blockUserList, Pageable pageable);
    @Query(value = "SELECT DISTINCT p FROM Post p JOIN FETCH p.user WHERE p.id IN (SELECT c.post.id FROM Comment c WHERE c.user.id = :userId) OR p.id IN (SELECT pl.post.id FROM PostLike pl WHERE pl.user.id = :userId)",
        countQuery = "SELECT COUNT(DISTINCT p) FROM Post p WHERE p.id IN (SELECT c.post.id FROM Comment c WHERE c.user.id = :userId) OR p.id IN (SELECT pl.post.id FROM PostLike pl WHERE pl.user.id = :userId)")
    Page<Post> findCommentedOrLikedPostListByUserId(Long userId, Pageable pageable);
}
