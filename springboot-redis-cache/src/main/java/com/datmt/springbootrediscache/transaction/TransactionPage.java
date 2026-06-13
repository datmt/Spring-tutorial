package com.datmt.springbootrediscache.transaction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionPage implements Serializable {
    private List<TransactionDTO> content;
    private int page;
    private int size;
    private long totalElements;
    private int totalPages;
}
