package ifTG.travelPlan.service.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ifTG.travelPlan.service.api.dto.ContentType;
import ifTG.travelPlan.service.api.dto.tourapi.TourApiResponseDto;
import ifTG.travelPlan.service.api.dto.tourapi.areabasedsync.AreaBasedSyncListDto;
import ifTG.travelPlan.service.api.dto.tourapi.detailintro.DetailIntroDto;
import ifTG.travelPlan.service.api.dto.tourapi.detailintro.sightseeing.SightSeeingDetailIntroDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.LinkedHashMap;

@Getter
@RequiredArgsConstructor
@Slf4j
@Service
public class TourApiDetailIntroImpl implements TourApiDetailIntro {

    @Value("${api.tour.url.base_url}")
    private String basedURL;
    private final RestTemplate restTemplate;
    private final TourApi tourApi;
    @Value("${api.tour.url.detail_intro}")
    private String selectIntroductionIntro;
    @Override
    public <T extends DetailIntroDto> T selectIntroductionIntro(Class<T> type, Long contentId, ContentType contentTypeId){
        String url = basedURL + selectIntroductionIntro;

        UriComponents builder = tourApi.getUriComponentToTourApi(UriComponentsBuilder.fromUriString(url), 0)
                .queryParam("contentId", contentId)
                .queryParam("contentTypeId", contentTypeId.getValue())
                .build(true);
        log.info("tour api url = {}", builder.toUriString());

        HttpEntity<?> entity = new HttpEntity<>(new HttpHeaders());
        TourApiResponseDto<LinkedHashMap<?,?>> responseJson = restTemplate.exchange(
                builder.toUri(),
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<TourApiResponseDto<LinkedHashMap<?,?>>>(){}).getBody();


        if (responseJson==null)throw new RuntimeException("Fail Connect to Tour API");
        return new ObjectMapper().convertValue(responseJson.getResponse().getBody().getItems(), type);
    }

}
