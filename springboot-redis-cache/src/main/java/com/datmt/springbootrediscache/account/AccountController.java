package com.datmt.springbootrediscache.account;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService service;
    private final AccountRepository repository;

    public AccountController(AccountService service, AccountRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    // Cached — first call slow, subsequent calls instant
    @GetMapping("/{id}")
    public AccountDTO getAccount(@PathVariable String id) {
        return service.getAccount(id);
    }

    // Direct DB — always slow, for comparison
    @GetMapping("/{id}/no-cache")
    public AccountDTO getAccountNoCache(@PathVariable String id) {
        return repository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException(id));
    }

    // Evict strategy: cache cleared, next read repopulates from DB
    @PutMapping("/{id}/evict")
    public AccountDTO updateEvict(@PathVariable String id, @RequestBody AccountDTO dto) {
        return service.updateAccountEvict(id, dto);
    }

    // Put strategy: cache updated inline, no stale window
    @PutMapping("/{id}/put")
    public AccountDTO updatePut(@PathVariable String id, @RequestBody AccountDTO dto) {
        return service.updateAccountPut(id, dto);
    }

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<String> handleNotFound(AccountNotFoundException ex) {
        return ResponseEntity.notFound().build();
    }
}
