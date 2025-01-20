package uz.result.drxoshimov.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import uz.result.drxoshimov.response.ApiResponse;
import uz.result.drxoshimov.utils.ApiConstants;

@Configuration
@RequiredArgsConstructor
public class AuthKeyCheckInterceptor implements WebMvcConfigurer, HandlerInterceptor {

    public static final String AUTH_KEY = "auth-key";

    @Value("${auth.key}")
    private String authKey;

    private final ObjectMapper objectMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!request.getRequestURI().startsWith(ApiConstants.PREFIX)) {
            return true;
        }
        String authKeyHeader = request.getHeader(AUTH_KEY);
        boolean authKeyCheck = authKeyHeader != null && authKeyHeader.startsWith(authKey);

        if (!authKeyCheck) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            ApiResponse<?> apiResponse = new ApiResponse<>("Unauthorized", null);
            response.getOutputStream().write(objectMapper.writeValueAsBytes(apiResponse));
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.getOutputStream().flush();
            return false;
        }
        return true;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this);
    }

}
