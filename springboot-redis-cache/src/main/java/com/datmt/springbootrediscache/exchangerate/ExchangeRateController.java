package com.datmt.springbootrediscache.exchangerate;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/exchange-rates")
public class ExchangeRateController {

    private final ExchangeRateCache cache;

    public ExchangeRateController(ExchangeRateCache cache) {
        this.cache = cache;
    }

    // e.g. GET /exchange-rates/USD-EUR
    @GetMapping("/{pair}")
    public Double getRate(@PathVariable String pair) {
        return cache.getRate(pair.toUpperCase());
    }

    // Manual refresh trigger for demo — normally driven by scheduler
    @PostMapping("/refresh")
    public String refresh() {
        cache.refreshAllRates();
        return "Refreshed all rates via pipeline";
    }
}
