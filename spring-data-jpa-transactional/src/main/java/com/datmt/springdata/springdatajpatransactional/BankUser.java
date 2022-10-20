package com.datmt.springdata.springdatajpatransactional;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="bank_user")
@Getter
@Setter
public class BankUser {
    @Id
    private Long id;

    private String name;

    private Long balance;
}