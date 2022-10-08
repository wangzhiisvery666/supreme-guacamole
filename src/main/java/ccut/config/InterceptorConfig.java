package ccut.config;

import ccut.interceptor.JWTInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Autowired
    JWTInterceptor jwtInterceptor;

    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration interceptorRegistration = registry.addInterceptor(jwtInterceptor);
        interceptorRegistration
                //拦截所有
                .addPathPatterns("/**")
                //排除路径
                .excludePathPatterns("/LunBo/**", "/login/**", "/register/**", "/admin/**", "/StoreManage/**","/StoreAudit/**")
                .excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v3/**", "/swagger-ui/**");
    }

}

