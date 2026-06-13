package com.datmt.springbootrediscache.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO implements Serializable {
    private String id;
    private String ownerName;
    private BigDecimal balance;
    private String type; // SAVINGS | CURRENT
}
