package com.datmt.spring.batch.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;
@Data
@AllArgsConstructor
public class TargetUser {
    String password;
    String email;
    String firstName;
    int age;
    String lastName;
    Date createdDate;
    Date updatedDate;

}
