package com.datmt.spring.reactivesse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.time.Duration;

@SpringBootApplication
public class ReactiveSseApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReactiveSseApplication.class, args);
    }

    public static Sinks.Many<BTCPrice> btcPriceSinks = Sinks.many()
            .multicast()
            .onBackpressureBuffer();

    @Controller
    public static class HomeController {

        @GetMapping("/")
        public String home() {
            return "home";
        }

        @GetMapping("/sse")
        public String sse() {
            return "sse";
        }

    }

    @RestController
    @RequestMapping("/sse")
    public static class SSEController {

        @RequestMapping(method = RequestMethod.GET, value = "/dogs",
                produces = "text/event-stream"
        )
        public Flux<ServerSentEvent<Dog>> dogStream() {
            return Flux.interval(Duration.ofSeconds(2))
                    .map(sequence -> ServerSentEvent.builder(new Dog(sequence, "Dog " + sequence)).build());
        }

        @RequestMapping(method = RequestMethod.GET, value = "/btc",
                produces = "text/event-stream"
        )
        public Flux<BTCPrice> btcEvents() {
            return ReactiveSseApplication.btcPriceSinks.asFlux();
        }
    }

    @RestController
    @RequestMapping("/hook")
    public static class HookController {
        @GetMapping("/btc/{price}/{currency}")
        public void btcEvent(@PathVariable double price, @PathVariable String currency) {
            ReactiveSseApplication.btcPriceSinks.tryEmitNext(new BTCPrice(currency, price));
        }
    }


    public record Dog(long id, String name) {
    }

    public record BTCPrice(String currency, double price) {
    }

}
