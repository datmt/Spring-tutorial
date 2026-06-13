package com.datmt.springbootrediscache.transaction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO implements Serializable {
    private String id;
    private String accountId;
    private BigDecimal amount;
    private String status; // PENDING | COMPLETED | FAILED
    private LocalDateTime createdAt;
}
