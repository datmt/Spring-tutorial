package com.datmt.spring.batch.models;

import lombok.Data;

import java.sql.Date;

@Data
public class OriginalUser {
    String firstName;
    String lastName;
    String email;
    Date birthday;
    String password;
    Date createdAt;
    Date updatedAt;
}
