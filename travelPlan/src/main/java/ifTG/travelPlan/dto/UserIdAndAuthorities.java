package ifTG.travelPlan.dto;

import lombok.*;

import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserIdAndAuthorities {
    private String userId;
    private List<String> authorities;
}
