package ifTG.travelPlan.filter;

import com.google.gson.JsonObject;
import ifTG.travelPlan.exception.StatusCode;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        log.info("message = {}", authException.getMessage());
        log.debug("AuthenticationEntryPoint : {}", (Object) authException.getStackTrace());
        log.info("request = servletPath: {}, param-key: {}, param-value: {}, method: {}", request.getServletPath(), request.getParameterMap().keySet(), request.getParameterMap().values(), request.getMethod());


        StatusCode statusCode = StatusCode.AUTHENTICATION_FAILED;
        response.setStatus(statusCode.getHttpStatus().value());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("message", statusCode.getMessage());
        jsonObject.addProperty("code", statusCode.getCode());

        response.getWriter().write(jsonObject.toString());
        response.getWriter().flush();
    }
}
