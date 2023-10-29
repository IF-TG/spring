package ifTG.travelPlan.dto.travel;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class DestinationDto {
    private Long id;
    private String name;
    private String address;
    private int likes;
}
