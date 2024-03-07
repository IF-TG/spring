package ifTG.travelPlan.service.api;

import ifTG.travelPlan.service.api.dto.naver.NaverBlogApiDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.buf.Utf8Encoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
@Deprecated
@Slf4j
public class NaverApiImpl implements NaverApi{
    private final RestTemplate restTemplate;
    @Value("${api.naver.blog}")
    private String blogApiUri;

    @Override
    public NaverBlogApiDto selectBlogInfo(String query){
        UriComponents uriComponents = UriComponentsBuilder.fromUriString(blogApiUri)
                                                          .queryParam("query", URLEncoder.encode(query, StandardCharsets.UTF_8)).build();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("X-Naver-Client-Id", "");
        httpHeaders.add("X-Naver-Client-Secret", "");
        HttpEntity<?> entity = new HttpEntity<>(httpHeaders);
        ResponseEntity<NaverBlogApiDto> response = restTemplate.exchange(uriComponents.toUri(), HttpMethod.GET, entity, NaverBlogApiDto.class);
        log.info("value = {}",response.getBody());
        return response.getBody();
    }
}
