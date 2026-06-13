package com.datmt.springbootrediscache.account;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Scenario 1 — Cache-aside with @Cacheable / @CacheEvict / @CachePut.
 *
 * TTL for "accounts" cache is 10 min (configured in SpringbootRedisCacheApplication).
 *
 * Demo flow:
 *   1. GET /accounts/ACC001      -> 800 ms (DB hit, cache miss)
 *   2. GET /accounts/ACC001      -> ~0 ms  (Redis hit)
 *   3. PUT /accounts/ACC001      -> evicts cache
 *   4. GET /accounts/ACC001      -> 800 ms again (cache miss)
 */
@Service
public class AccountService {

    private final AccountRepository repository;

    public AccountService(AccountRepository repository) {
        this.repository = repository;
    }

    @Cacheable(value = "accounts", key = "#accountId")
    public AccountDTO getAccount(String accountId) {
        return repository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException(accountId));
    }

    // Evict on update — next read hits DB and repopulates cache
    @CacheEvict(value = "accounts", key = "#accountId")
    public AccountDTO updateAccountEvict(String accountId, AccountDTO dto) {
        dto.setId(accountId);
        repository.save(dto);
        return dto;
    }

    // CachePut — updates cache inline instead of evicting
    @CachePut(value = "accounts", key = "#accountId")
    public AccountDTO updateAccountPut(String accountId, AccountDTO dto) {
        dto.setId(accountId);
        repository.save(dto);
        return dto;
    }
}
