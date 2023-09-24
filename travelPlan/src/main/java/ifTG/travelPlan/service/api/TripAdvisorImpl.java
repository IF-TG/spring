package ifTG.travelPlan.service.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
@Slf4j
@RequiredArgsConstructor
@Deprecated
public class TripAdvisorImpl implements TripAdvisor{
    @Value("${api.trip_advisor.secure_key}")
    private String secureKey;
    @Value("${api.trip_advisor.url.base_url}")
    private String baseUrl;
    @Value("${api.trip_advisor.url.location_details}")
    private String locationDetailPath;

    private final RestTemplate restTemplate;


    public String searchLocationDetail(Long id){
        String url = baseUrl + "/" + id + locationDetailPath;
        UriComponents builder = UriComponentsBuilder.fromUriString(url)
                .queryParam("key", secureKey)
                .queryParam("language", "ko")
                .queryParam("currency", "USD")
                .build();

        //restTemplate.exchange();
        return "d";
    }
}
