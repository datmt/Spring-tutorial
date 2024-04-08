package com.datmt.logging.models;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Order {
   private String orderId;
   private String customerName;
   private String orderDate; 
}
