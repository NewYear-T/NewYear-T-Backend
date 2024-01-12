//package com.example.newyear.config;
//
//import jakarta.servlet.http.HttpServletRequest;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//
//import java.util.Collections;
//
//@Configuration
//@RequiredArgsConstructor
//@EnableWebSecurity
//public class SecurityConfig {
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
//        http.cors(corsCustomizer -> corsCustomizer.configurationSource(new CorsConfigurationSource() {
//            @Override
//            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
//                CorsConfiguration config = new CorsConfiguration();
//                config.addAllowedOriginPattern("*");
//                config.addAllowedHeader("*");
//                config.addAllowedMethod("*");
//                config.setAllowCredentials(true);
//
//                return config;
//            }
//        }));
//        http
//                .csrf((csrf) -> csrf.disable())
//                .authorizeHttpRequests((authorizeHttpRequests) ->
//                                authorizeHttpRequests
//                                .requestMatchers("/**").permitAll()
//                );
//
//
//        // admin은 모든 접근에 대해 허락하고
//        // 게스트는 특정 url만 허락할 필요있음 : ex ) 검색, ...
//
//        return http.build();
//    }
//}
