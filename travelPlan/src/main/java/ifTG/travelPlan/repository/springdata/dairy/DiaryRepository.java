package ifTG.travelPlan.repository.springdata.dairy;

import ifTG.travelPlan.domain.diary.Diary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DiaryRepository extends JpaRepository<Diary, Long> {
    @Query(value = "SELECT d FROM Diary d Where d.travelPlan.id = :id ORDER BY d.createAt DESC ")
    Page<Diary> findAllWithUser(Pageable pageable, @Param("id")Long id);
}
