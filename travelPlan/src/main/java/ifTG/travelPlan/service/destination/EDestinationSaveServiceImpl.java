package ifTG.travelPlan.service.destination;

import ifTG.travelPlan.domain.travel.Destination;
import ifTG.travelPlan.elasticsearch.domain.EDestination;
import ifTG.travelPlan.elasticsearch.repository.EDestinationRepository;
import ifTG.travelPlan.service.travelplan.search.machineleaning.destinationvector.DestinationOverViewVectorV2;
import ifTG.travelPlan.service.travelplan.search.machineleaning.destinationvector.TextRank;
import ifTG.travelPlan.service.travelplan.search.machineleaning.vector.VectorAverage;

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
    private final VectorAverage vectorAverage;
    private final DestinationOverViewVectorV2 destinationOverViewVectorV2;

    @Override
    public void saveEDestination(List<Destination> destinationList) {
        destinationList.forEach(d->{
            List<String> keywordList = textRank.textRank(d.getOverview());
            double[] vectorAverageArray = null;
            if (keywordList.size()==10){
                vectorAverageArray = vectorAverage.getVectorAverage(keywordList.stream().map(destinationOverViewVectorV2::getVectorByString).toList());
            }

            EDestination eDestination = getEDestination(d, keywordList, vectorAverageArray);
            eDestinationRepository.save(eDestination);
        });
    }

    private static EDestination getEDestination(Destination d, List<String> keywordList, double[] vectorAverageArray) {
        return EDestination.builder()
                .id(d.getId())
                .title(d.getTitle())
                .keywordList(keywordList)
                .thumbnailUrl(d.getThumbNail())
                .info(d.getOverview())
                .address(d.getAddress())
                .category(d.getCategory())
                .contentType(d.getContentType())
                .embedding(vectorAverageArray)
                .build();
    }
}
