package ifTG.travelPlan.service.destination;

import ifTG.travelPlan.domain.travel.Destination;
import ifTG.travelPlan.elasticsearch.domain.EDestination;
import ifTG.travelPlan.elasticsearch.repository.EDestinationRepository;
import ifTG.travelPlan.service.travelplan.search.TextRank;
import ifTG.travelPlan.service.travelplan.search.VectorAverage;
import ifTG.travelPlan.service.travelplan.search.Word2Vec;
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
    private final Word2Vec word2Vec;

    @Override
    public void saveEDestination(List<Destination> destinationList) {
        destinationList.forEach(d->{
            List<String> keywordList = textRank.textRank(d.getOverview());
            double[] vectorAverageArray = null;
            if (keywordList.size()==10){
                vectorAverageArray = vectorAverage.getVectorAverage(keywordList.stream().map(word2Vec::getVectorByString).toList());
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
