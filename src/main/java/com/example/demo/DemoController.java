package com.example.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/demo")
public class DemoController {
    @GetMapping("/hello")
    @PreAuthorize("hasAuthority('READ')")
    public Mono<ResponseEntity<?>> getHello(@AuthenticationPrincipal UserDetails userDetails) {
        return Mono.just(ResponseEntity.ok().body(userDetails));
    }

}
