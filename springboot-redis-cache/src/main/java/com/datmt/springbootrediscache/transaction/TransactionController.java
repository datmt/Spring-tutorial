package com.datmt.springbootrediscache.transaction;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionQueryService service;

    public TransactionController(TransactionQueryService service) {
        this.service = service;
    }

    /**
     * Try:
     *   /transactions/search                              — all transactions
     *   /transactions/search?accountId=ACC001             — filter by account
     *   /transactions/search?status=PENDING               — filter by status
     *   /transactions/search?accountId=ACC001&status=PENDING&page=0&size=3
     */
    @GetMapping("/search")
    public TransactionPage search(
            @RequestParam(required = false) String accountId,
            @RequestParam(required = false) String status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        TransactionFilter filter = new TransactionFilter();
        filter.setAccountId(accountId);
        filter.setStatus(status);
        return service.search(filter, page, size);
    }
}
