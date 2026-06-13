package com.datmt.springbootrediscache.transaction;

import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class TransactionRepository {

    private final Map<String, TransactionDTO> store = new ConcurrentHashMap<>();

    public TransactionRepository() {
        String[] accounts = {"ACC001", "ACC002", "ACC003"};
        String[] statuses = {"PENDING", "COMPLETED", "FAILED"};
        LocalDateTime base = LocalDateTime.now().minusDays(7);

        for (int i = 1; i <= 30; i++) {
            String id = "TXN" + String.format("%03d", i);
            String account = accounts[i % accounts.length];
            String status = statuses[i % statuses.length];
            store.put(id, new TransactionDTO(id, account,
                    BigDecimal.valueOf(100L * i), status, base.plusHours(i)));
        }
    }

    public TransactionPage search(TransactionFilter filter, int page, int size) {
        simulateSlowQuery();

        List<TransactionDTO> filtered = store.values().stream()
                .filter(t -> filter.getAccountId() == null || filter.getAccountId().equals(t.getAccountId()))
                .filter(t -> filter.getStatus() == null || filter.getStatus().equalsIgnoreCase(t.getStatus()))
                .sorted((a, b) -> b.getCreatedAt().compareTo(a.getCreatedAt()))
                .collect(Collectors.toList());

        long total = filtered.size();
        int totalPages = (int) Math.ceil((double) total / size);
        int from = Math.min(page * size, filtered.size());
        int to = Math.min(from + size, filtered.size());

        return new TransactionPage(new ArrayList<>(filtered.subList(from, to)), page, size, total, totalPages);
    }

    private void simulateSlowQuery() {
        try { Thread.sleep(600); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }
}
