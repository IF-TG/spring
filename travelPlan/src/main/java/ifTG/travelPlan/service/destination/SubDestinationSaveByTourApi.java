package ifTG.travelPlan.service.destination;

import ifTG.travelPlan.domain.travel.Destination;

import java.util.List;

public interface SubDestinationSaveByTourApi {
    void save(List<Destination> destinationList);
}
