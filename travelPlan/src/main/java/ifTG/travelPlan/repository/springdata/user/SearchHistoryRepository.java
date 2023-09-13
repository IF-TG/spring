package ifTG.travelPlan.repository.springdata.user;

import ifTG.travelPlan.domain.user.SearchHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface SearchHistoryRepository extends JpaRepository<SearchHistory, Long> {
    @Query("SELECT sh FROM SearchHistory sh WHERE sh.user.id = :userId")
    Page<SearchHistory> findAllByUserId(Long userId, Pageable pageable);

    @Query("SELECT sh FROM SearchHistory sh Where sh.user.id = :userId AND sh.history = :history")
    Optional<SearchHistory> findByUserIdAndHistory(Long userId, String history);
}
