package com.petr.debug.testdebugflux.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MainService {

    public Mono<String> getNull(String value) {
        return Mono.just(value)
                .map(String::toUpperCase)
                .map(i -> null);
    }

    public Mono<String> aggregation(String value) {
        Mono<String> newValue = Mono.just(value);
        Mono<String> empty = Mono.empty();
        return Flux.zip(newValue, empty)
                .map(tuple -> tuple.getT1() + tuple.getT2())
                .single();
    }
}
