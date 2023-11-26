package ifTG.travelPlan.domain.user;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import java.util.HashMap;
import java.util.Map;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="user_vectors")
public class UserVector {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name ="user_vector_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", unique = true)
    private User user;

    @Type(JsonType.class)
    @Column(columnDefinition = "longtext")
    private Map<Integer, Double> vector = new HashMap<>();

    @Builder
    public UserVector(User user, Map<Integer, Double> vector) {
        this.user = user;
        this.vector = vector;
    }

    public void updateVector(Map<Integer, Double> vector) {
        this.vector = vector;
    }
}
