package ifTG.travelPlan.repository.springdata.dairy;

import ifTG.travelPlan.domain.diary.Diary;
import ifTG.travelPlan.domain.post.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
    @Query(value = "SELECT d FROM Diary d Where d.user.id = :id ORDER BY d.createAt DESC ")
    Page<Diary> findAllWithUser(@Param("id")Long id, Pageable pageable);
}
