/**
 * 버전 문제로 삭제
 */

/*
package ifTG.travelPlan.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


import java.util.ArrayList;

@EnableWebMvc
public class SwaggerConfig {

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("ifTG.travelPlan"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(metaData());
    }

    private ApiInfo metaData() {
        return new ApiInfo(
                "TravelPlan",
                "TravelPlan",
                "0.9.0",
                "없음",
                new Contact("ImKyeongWan", "localhost:8080", "ruddhks9937@gmail.com"),
                "없음",
                "없음",
                new ArrayList<VendorExtension>());
    }
}
*/

