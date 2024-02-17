package ifTG.travelPlan.service.destination;

import ifTG.travelPlan.domain.travel.Destination;
import ifTG.travelPlan.elasticsearch.domain.EDestination;
import ifTG.travelPlan.elasticsearch.repository.EDestinationRepository;
import ifTG.travelPlan.service.travelplan.search.machineleaning.destinationvector.destination.docvector.DestinationVector;
import ifTG.travelPlan.service.travelplan.search.machineleaning.destinationvector.TextRank;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class EDestinationSaveServiceImpl implements EDestinationSaveService{
    private final EDestinationRepository eDestinationRepository;
    private final TextRank textRank;
    private final DestinationVector destinationVector;

    @Override
    public void saveEDestination(List<Destination> destinationList) {
        log.info("destinationList size = {}", destinationList.size());
        destinationList.forEach(d->{
            List<String> keywordList = textRank.textRank(d.getOverview());
            double[] vectorByDestinationId = destinationVector.getVectorByDestinationId(d.getId());
            EDestination eDestination = getEDestination(d, keywordList, vectorByDestinationId);
            eDestinationRepository.save(eDestination);
        });
    }

    private static EDestination getEDestination(Destination d, List<String> keywordList, double[] vector) {
        return EDestination.builder()
                .id(d.getId())
                .title(d.getTitle())
                .keywordList(keywordList)
                .thumbnailUrl(d.getThumbNail())
                .info(d.getOverview())
                .address(d.getAddress())
                .category(d.getCategory())
                .contentType(d.getContentType())
                .embedding(vector)
                .build();
    }
}
