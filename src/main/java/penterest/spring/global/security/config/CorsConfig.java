package penterest.spring.global.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*"); // 허용할 원본 도메인 설정
        config.addAllowedMethod("*"); // 모든 HTTP 메서드 허용
        config.addAllowedHeader("*"); // 모든 HTTP 헤더 허용
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
