package ifTG.travelPlan.configuration;

import ifTG.travelPlan.aop.AuthenticationOptionalUserResolver;
import ifTG.travelPlan.aop.AuthenticationUserResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class WebConfigure implements WebMvcConfigurer {
    private final AuthenticationOptionalUserResolver authenticationOptionalUserResolver;
    private final AuthenticationUserResolver authenticationUserResolver;
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        //WebMvcConfigurer.super.addArgumentResolvers(resolvers);
        resolvers.add(authenticationOptionalUserResolver);
        resolvers.add(authenticationUserResolver);
    }
}
