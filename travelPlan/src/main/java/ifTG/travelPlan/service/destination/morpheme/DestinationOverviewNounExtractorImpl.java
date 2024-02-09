package ifTG.travelPlan.service.destination.morpheme;

import ifTG.travelPlan.domain.travel.Destination;
import ifTG.travelPlan.repository.springdata.travel.DestinationRepository;
import ifTG.travelPlan.service.travelplan.search.machineleaning.dictionary.Morpheme;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    @Transactional(readOnly = true)
    public Map<Long, List<String>> findAllNounMappingByDestination(){
        List<Destination> destinationList = destinationRepository.findAll();
        Map<Long, List<String>> resultMapping = new HashMap<>();
        destinationList.forEach(d->{
            List<String> nounList = morpheme.getNounByString(d.getOverview());
            if (nounList.size()>=10) resultMapping.put(d.getId(), nounList);
        });
        return resultMapping;
    }

    @Override
    public Integer getIdx(String s) {
        return morpheme.getIdx(s);
    }

}
