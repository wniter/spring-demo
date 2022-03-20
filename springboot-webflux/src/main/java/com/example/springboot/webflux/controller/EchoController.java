package com.example.springboot.webflux.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class EchoController {

    @GetMapping("/echo")
    public Mono<String> sayHelloWorld() {
        return Mono.just("Echo!");
    }
}
