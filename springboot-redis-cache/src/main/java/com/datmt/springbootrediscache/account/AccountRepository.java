package com.datmt.springbootrediscache.account;

import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class AccountRepository {

    private final Map<String, AccountDTO> store = new ConcurrentHashMap<>();

    public AccountRepository() {
        store.put("ACC001", new AccountDTO("ACC001", "Alice",  BigDecimal.valueOf(50_000), "SAVINGS"));
        store.put("ACC002", new AccountDTO("ACC002", "Bob",    BigDecimal.valueOf(120_000), "CURRENT"));
        store.put("ACC003", new AccountDTO("ACC003", "Charlie", BigDecimal.valueOf(8_500), "SAVINGS"));
    }

    public Optional<AccountDTO> findById(String id) {
        simulateSlowQuery();
        return Optional.ofNullable(store.get(id));
    }

    public void save(AccountDTO dto) {
        store.put(dto.getId(), dto);
    }

    private void simulateSlowQuery() {
        try { Thread.sleep(800); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }
}
