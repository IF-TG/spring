package ifTG.travelPlan.configuration;

import co.elastic.clients.elasticsearch.nodes.Http;
import ifTG.travelPlan.dto.Roles;
import ifTG.travelPlan.filter.CustomAccessDeniedHandler;
import ifTG.travelPlan.filter.CustomAuthenticationEntryPoint;
import ifTG.travelPlan.filter.CustomExceptionHandlerFilter;
import ifTG.travelPlan.filter.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final CustomExceptionHandlerFilter customExceptionHandlerFilter;
    private final CustomAuthenticationEntryPoint entryPoint;
    private final CustomAccessDeniedHandler deniedHandler;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .formLogin(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .rememberMe(AbstractHttpConfigurer::disable)
                .exceptionHandling(ex-> ex
                        .authenticationEntryPoint(entryPoint)
                        .accessDeniedHandler(deniedHandler)
                )
                .authorizeHttpRequests(auth-> {
                    auth
                            .requestMatchers(HttpMethod.GET, "/comment").hasAnyRole(Roles.ANONYMOUS.role(), Roles.OAUTH2_USER.role())
                            .requestMatchers(HttpMethod.DELETE, "/comment").hasRole(Roles.OAUTH2_USER.role())
                            .requestMatchers(HttpMethod.POST, "/comment").hasRole(Roles.OAUTH2_USER.role())
                            .requestMatchers(HttpMethod.PUT, "/comment").hasRole(Roles.OAUTH2_USER.role())
                            .requestMatchers(HttpMethod.POST, "/comment/like").hasRole(Roles.OAUTH2_USER.role())
                            .requestMatchers("/comment/nestedComment").hasRole(Roles.OAUTH2_USER.role())
                            .requestMatchers("/comment/nestedComment/like").hasRole(Roles.OAUTH2_USER.role())
                            .requestMatchers("/destination/detail").hasAnyRole(Roles.OAUTH2_USER.role(),Roles.ANONYMOUS.role())
                            .requestMatchers("/destination/search").hasRole(Roles.OAUTH2_USER.role())
                            .requestMatchers("/destination/recommend").hasAnyRole(Roles.OAUTH2_USER.role(), Roles.ANONYMOUS.role())
                            .requestMatchers("/destination/scrap/**").hasRole(Roles.OAUTH2_USER.role())
                            .requestMatchers("/destination/route").hasAnyRole(Roles.OAUTH2_USER.role())
                            .requestMatchers("/post/user").hasRole(Roles.OAUTH2_USER.role())
                            .requestMatchers("/post/likedCommented").hasRole(Roles.OAUTH2_USER.role())
                            .requestMatchers("/post").hasRole(Roles.OAUTH2_USER.role())
                            .requestMatchers("/post/detail").hasAnyRole(Roles.OAUTH2_USER.role(), Roles.ANONYMOUS.role())
                            .requestMatchers("/post/like/**").hasRole(Roles.OAUTH2_USER.role())
                            .requestMatchers("/posts").hasAnyRole(Roles.OAUTH2_USER.role(), Roles.ANONYMOUS.role())
                            .requestMatchers("/post/scrap/**").hasRole(Roles.OAUTH2_USER.role())
                            .requestMatchers("/post/search").hasAnyRole(Roles.OAUTH2_USER.role(), Roles.ANONYMOUS.role())
                            .requestMatchers("/scrap/delete").hasRole(Roles.OAUTH2_USER.role())
                            .requestMatchers("/travelPlan/**").hasRole(Roles.OAUTH2_USER.role())
                            .requestMatchers("/blockUser/**").hasRole(Roles.OAUTH2_USER.role())
                            .requestMatchers("/nickname/**").hasRole(Roles.OAUTH2_USER.role())
                            .requestMatchers("/profile/upload").hasRole(Roles.OAUTH2_USER.role())
                            .requestMatchers("/profile").hasRole(Roles.OAUTH2_USER.role())
                            .requestMatchers("/profile/original").hasAnyRole(Roles.OAUTH2_USER.role(), Roles.ANONYMOUS.role())
                            .requestMatchers("/scrap").hasRole(Roles.OAUTH2_USER.role())
                            .requestMatchers("/searchHistory").hasRole(Roles.OAUTH2_USER.role())
                            .requestMatchers("/swagger-ui/**").permitAll()
                            .requestMatchers("/v3/api-docs/**").permitAll()
                            .anyRequest().hasAnyRole("OAUTH2_USER", "ANONYMOUS");
                })
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(customExceptionHandlerFilter, JwtAuthenticationFilter.class)
                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }
}
