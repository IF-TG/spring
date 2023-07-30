package ifTG.travelPlan.service.api;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Base64;

@Service
@RequiredArgsConstructor
public class TourApiImpl implements TourApi{
    @Value("${api.tour.url.base_url}")
    private String basedURL;
    @Value("${api.tour.secure_key}")
    private String secureKey;
    @Value("${api.tour.url.search_keyword}")
    private String searchKeywordMethod;

    private final RestTemplate restTemplate;

    public String searchKeyword(String keyword, int page) throws UnsupportedEncodingException {
        keyword = URLEncoder.encode(keyword, "UTF-8");
        String url = basedURL + searchKeywordMethod;
        UriComponents builder = UriComponentsBuilder.fromUriString(url)
                .queryParam("numOfRows", 100)
                .queryParam("pageNo", page)
                .queryParam("MobileOS", "ETC")
                .queryParam("MobileApp", "travelPlan")
                .queryParam("_type", "json")
                .queryParam("keyword", keyword)
                .queryParam("serviceKey", secureKey).build(true);
        HttpEntity<?> entity = new HttpEntity<>(new HttpHeaders());
        System.out.println("builder = " + builder.toUriString());
        ResponseEntity<String> response = restTemplate.exchange(builder.toUri() , HttpMethod.GET, entity, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else {
            throw new RuntimeException("TOUR API 호출에 실패하였습니다.");
        }
    }
}
