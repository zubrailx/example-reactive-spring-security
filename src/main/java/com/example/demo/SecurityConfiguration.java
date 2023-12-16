package com.example.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository;

@Configuration
@EnableWebFluxSecurity
@RequiredArgsConstructor
@EnableReactiveMethodSecurity
public class SecurityConfiguration {

  private final CustomAuthFilter authFilter;

  private final String[] WHITE_LIST_URLS = {
      "/api-docs",
      "/api-docs/**",
      "/swagger-ui",
      "/swagger-ui/**",
      "/webjars/**",
  };

  @Bean
  public SecurityWebFilterChain filterChain(ServerHttpSecurity http) {
    http
        .authorizeExchange(exchange -> exchange
            .pathMatchers(WHITE_LIST_URLS).permitAll()
            .anyExchange().authenticated()
        )
        .csrf(ServerHttpSecurity.CsrfSpec::disable)
        .cors(ServerHttpSecurity.CorsSpec::disable)
        .formLogin(ServerHttpSecurity.FormLoginSpec::disable)
        .logout(ServerHttpSecurity.LogoutSpec::disable)
        .addFilterAt(authFilter, SecurityWebFiltersOrder.AUTHENTICATION);

    return http.build();
  }

}
