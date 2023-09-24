package ifTG.travelPlan.service.destination;

import ifTG.travelPlan.controller.dto.RequestSearchDestinationDto;
import ifTG.travelPlan.elasticsearch.domain.EDestination;
import ifTG.travelPlan.elasticsearch.repository.EDestinationCustomRepository;
import ifTG.travelPlan.service.api.ChatGPT;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DestinationServiceImpl implements DestinationService{
    private final ChatGPT chatGPT;
    private final EDestinationCustomRepository eDestinationCustomRepository;
    @Override
    public List<EDestination> findAllByKeyword(RequestSearchDestinationDto dto){
        List<String> relatedKeywordList  = chatGPT.findRelatedKeywords(dto.getKeyword());
        log.info("{}",relatedKeywordList.toString());
        return eDestinationCustomRepository.findAllByUserKeywordAndGPTKeywordList(dto.getKeyword(), relatedKeywordList, dto.getPageable());
    }
}
