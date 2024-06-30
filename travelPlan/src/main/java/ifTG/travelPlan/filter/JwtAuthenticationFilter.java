package ifTG.travelPlan.filter;

import ifTG.travelPlan.exception.StatusCode;
import ifTG.travelPlan.domain.user.User;
import ifTG.travelPlan.dto.StatusCodeMessageDto;
import ifTG.travelPlan.dto.UserIdAndAuthorities;
import ifTG.travelPlan.exception.CustomErrorException;
import ifTG.travelPlan.repository.springdata.user.UserRepository;
import ifTG.travelPlan.service.travelplan.search.machineleaning.util.Check;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final static String AUTHENTICATION_PROPERTY = "Authorization";
    private final RestTemplate restTemplate;
    private final UserRepository userRepository;

    @Value("${security.verify-server.uri}")
    private String authenticationServerUri;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("requset = {}",request);
        String jwt = request.getHeader(AUTHENTICATION_PROPERTY);
        if (jwt != null && jwt.startsWith("Bearer ")) {
            jwt =  jwt.substring(7);
            log.info("{} request", jwt);
        }else{
            filterChain.doFilter(request, response);
            return;
        }
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(AUTHENTICATION_PROPERTY, jwt);

        HttpEntity<?> httpEntity = new HttpEntity<>(httpHeaders);
        try {
            UserIdAndAuthorities userIdAndAuthorities = restTemplate.exchange(authenticationServerUri, HttpMethod.GET, httpEntity, UserIdAndAuthorities.class).getBody();
            Check.notNull(userIdAndAuthorities, StatusCode.Internal_Server_Error);
            Check.notNull(userIdAndAuthorities.getAuthorities(), StatusCode.Internal_Server_Error);
            Check.notNull(userIdAndAuthorities.getUserId(), StatusCode.Internal_Server_Error);
            List<GrantedAuthority> grantedAuthorities = userIdAndAuthorities.getAuthorities().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
            String userId = userIdAndAuthorities.getUserId();
            createUserIfNotFound(userId);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userIdAndAuthorities.getUserId(), null, grantedAuthorities);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            filterChain. doFilter(request, response);
        }catch (HttpClientErrorException e){
            StatusCodeMessageDto dto = e.getResponseBodyAs(StatusCodeMessageDto.class);
            Check.notNull(dto, StatusCode.Internal_Server_Error);
            StatusCode stateCode = StatusCode.getStateCode(dto);
            Check.notNull(stateCode, StatusCode.Internal_Server_Error);
            throw new CustomErrorException(stateCode);
        }
    }

    private void createUserIfNotFound(String userId) {
        if (!userRepository.existsById(Long.valueOf(userId))){
            userRepository.save(new User(Long.valueOf(userId)));
        }
    }
}


