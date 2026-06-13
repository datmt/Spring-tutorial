package com.datmt.springbootrediscache.transaction;

import lombok.Data;

@Data
public class TransactionFilter {
    private String accountId;
    private String status;

    @Override
    public String toString() {
        return "accountId=" + accountId + "&status=" + status;
    }
}
