package ifTG.travelPlan.service.destination.morpheme;

import ifTG.travelPlan.domain.travel.Destination;
import ifTG.travelPlan.repository.springdata.travel.DestinationRepository;
import ifTG.travelPlan.service.travelplan.search.machineleaning.Morpheme;
import ifTG.travelPlan.service.travelplan.search.machineleaning.MorphemeImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DestinationOverviewNounExtractorImpl implements DestinationOverviewNounExtractor{
    private final DestinationRepository destinationRepository;
    private final Morpheme morpheme;

    @Override
    @Transactional(readOnly = true)
    public List<List<String>> findAllNounGroupByDestination(){
        List<Destination> destinationList = destinationRepository.findAll();
        List<String> allDestinationOverviewByOverView = destinationList.stream().map(Destination::getOverview).toList();
        return allDestinationOverviewByOverView.stream().map(morpheme::getNounByString).toList();
    }
}
