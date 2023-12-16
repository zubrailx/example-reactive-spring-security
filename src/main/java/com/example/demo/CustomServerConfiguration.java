package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class CustomServerConfiguration {

  @Bean
  public ReactiveAuthenticationManager reactiveAuthenticationManager() {
    return Mono::just;
  }
}
