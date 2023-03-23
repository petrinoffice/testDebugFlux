package com.petr.debug.testdebugflux.controller;

import com.petr.debug.testdebugflux.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@RestController
public class MainController {

    @Autowired
    private MainService mainService;

    /**
     * Wrong transformation Flux -> Mono
     */
    @GetMapping("/1")
    public Mono<String> single() {
        return Flux.just("first", "second")
//                .checkpoint("Checkpoint 1")
                .map(String::toUpperCase)
//                .checkpoint("Checkpoint 2")
                .single()
                .subscribeOn(Schedulers.parallel());
//                .checkpoint("Checkpoint 3");
    }

    /**
     * While processing get NullPointerException
     */
    @GetMapping("/2")
    public Mono<String> nullPointerException() {
        return Mono.just("first")
                .flatMap(mainService::getNull);
    }

    /**
     * Wrong aggregation information from Publishers
     */
    @GetMapping("/3")
    public Mono<String> aggregationException() {
        return Mono.just("first")
                .flatMap(mainService::aggregation);
    }
}
