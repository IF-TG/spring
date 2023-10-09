package ifTG.travelPlan.service.api;

import ifTG.travelPlan.service.api.dto.ContentType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

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
    public Object selectIntroductionIntro(String contentId, ContentType contentTypeId){
        String url = basedURL + selectIntroductionIntro;
        UriComponents builder = tourApi.getUriComponentToTourApi(UriComponentsBuilder.fromUriString(url), 0)
                .queryParam("contentId", contentId)
                .queryParam("contentTypeId", contentTypeId.getValue())
                .build(true);
        log.info("tour api url = {}", builder.toUriString());


        HttpEntity<?> entity = new HttpEntity<>(new HttpHeaders());
        ResponseEntity<Object> response = restTemplate.exchange(builder.toUri(), HttpMethod.GET, entity, Object.class);

        System.out.println(response.getBody());
        return response.getBody();

    }

}
